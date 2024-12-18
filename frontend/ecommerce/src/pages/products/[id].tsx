import "../../types";
import Product from "@/components/product";
import { useEffect, useState } from "react";
import { useRouter } from "next/router";
import Review from "@/components/review";
import axios from "axios";
import { useUser } from "../../context/UserContext";
import styles from "./Products.module.css";

export default function ProductDetails() {
  let [product, setProduct] = useState<product_t | null>(null);
  let [reviews, setReviews] = useState<review_t[]>([]);
  let [reviewText, setReviewText] = useState<string>("");
  let [rating, setRating] = useState<number>(5);
  let [error, setError] = useState<string>("");
  let [isEditMode, setIsEditMode] = useState<boolean>(false);
  let [editingReview, setEditingReview] = useState<review_t | null>(null);

  const router = useRouter();
  const { id } = router.query;
  const { user } = useUser();

  const fetchProduct = async (prodID: number) => {
    try {
      const res = await axios.get<product_t>(
        `http://localhost:8080/api/products/${prodID}`
      );
      setProduct(res.data);
    } catch (error) {
      console.error("Error fetching products: ", error);
    }
  };

  const fetchReviews = async (prodID: number) => {
    try {
      const res = await axios.get<review_t[]>(
        `http://localhost:8080/api/reviews/product/${prodID}`
      );
      setReviews(res.data);
    } catch (error) {
      console.error("Error fetching reviews: ", error);
    }
  };

  useEffect(() => {
    const prodID = Number(id);
    if (prodID) {
      fetchProduct(prodID);
      fetchReviews(prodID);
    }
  }, [id]);

  const refreshReviews = async () => {
    const prodID = Number(id);
    await fetchReviews(prodID);
  };

  const handleReviewSubmit = async (e: React.FormEvent) => {
    e.preventDefault();

    if (!product) {
      setError("Must be a valid product.");
      return;
    }

    if (!user) {
      setError("Must be logged in.");
      return;
    }

    if (!reviewText.trim()) {
      setError("Review text is required.");
      return;
    }

    if (rating < 1 || rating > 5) {
      setError("Rating must be between 1 and 5.");
      return;
    }

    setError("");

    const newReview: review_t = {
      reviewID: -1,
      user,
      product,
      rating,
      reviewComment: reviewText,
      datePosted: new Date(),
    };

    try {
      const response = await axios.post(
        "http://localhost:8080/api/reviews",
        newReview,
        {
          headers: {
            "Content-Type": "application/json",
          },
        }
      );

      await refreshReviews();

      setReviewText("");
      setRating(5);
    } catch (error) {
      console.error("Error submitting review:", error);
      setError("There was an issue submitting your review.");
    }
  };

  const handleEditReview = (review: review_t) => {
    setEditingReview(review);
    setReviewText(review.reviewComment);
    setRating(review.rating);
    setIsEditMode(true);
  };

  const handleUpdateReview = async (e: React.FormEvent) => {
    e.preventDefault();

    if (!editingReview) {
      return;
    }

    const updatedReview: review_t = {
      ...editingReview,
      reviewComment: reviewText,
      rating,
    };

    try {
      await axios.put(
        `http://localhost:8080/api/reviews/${editingReview.reviewID}`,
        updatedReview,
        {
          headers: {
            "Content-Type": "application/json",
          },
        }
      );

      await refreshReviews();
      setIsEditMode(false);
      setEditingReview(null);
      setReviewText("");
      setRating(5);
    } catch (error) {
      console.error("Error updating review:", error);
      setError("There was an issue updating your review.");
    }
  };

  const handleDeleteReview = async (reviewID: number) => {
    try {
      await axios.delete(`http://localhost:8080/api/reviews/${reviewID}`);

      await refreshReviews();
    } catch (error) {
      console.error("Error deleting review:", error);
      setError("There was an issue deleting your review.");
    }
  };

  const userHasReviewed = reviews.some(
    (review) => review.user.userID === user?.userID
  );

  return (
    <>
      <h1 className={styles.header}>
        {" "}
        {product?.brand} {product?.productName}{" "}
      </h1>
      {product ? (
        <Product key={product.productID} {...product} />
      ) : (
        <h1 className={styles.header}>Could not find product.</h1>
      )}

      <div>
        <h1 className={styles.header}>User Reviews</h1>
        {reviews.map((review) => (
          <div key={review.reviewID} className={styles.reviewContainer}>
            <Review review={review} refresh={refreshReviews} />
            {user?.userID === review.user.userID && (
              <div
                className={`${styles.reviewActions} ${styles.centerButtonContainer}`}
              >
                <button
                  className={styles.editButton}
                  onClick={() => handleEditReview(review)}
                >
                  Edit
                </button>
                <button
                  className={styles.deleteButton}
                  onClick={() => handleDeleteReview(review.reviewID)}
                >
                  Delete
                </button>
              </div>
            )}
          </div>
        ))}
      </div>
      {!userHasReviewed && !isEditMode && (
        <div className={styles.reviewForm}>
          <h3>Leave a Review</h3>
          <form onSubmit={handleReviewSubmit} className={styles.form}>
            <div className={styles.inputGroup}>
              <label htmlFor="reviewText" className={styles.label}>
                Review:
              </label>
              <textarea
                id="reviewText"
                value={reviewText}
                onChange={(e) => setReviewText(e.target.value)}
                className={styles.textarea}
                placeholder="Write your review here..."
                required
              />
            </div>

            <div className={styles.inputGroup}>
              <label htmlFor="rating" className={styles.label}>
                Rating:
              </label>
              <select
                id="rating"
                value={rating}
                onChange={(e) => setRating(Number(e.target.value))}
                className={styles.select}
                required
              >
                {[1, 2, 3, 4, 5].map((rate) => (
                  <option key={rate} value={rate}>
                    {rate}
                  </option>
                ))}
              </select>
            </div>

            {error && <div className={styles.error}>{error}</div>}

            <button type="submit" className={styles.button}>
              Submit Review
            </button>
          </form>
        </div>
      )}

      {isEditMode && (
        <div className={styles.reviewForm}>
          <h3>Edit Review</h3>
          <form onSubmit={handleUpdateReview} className={styles.form}>
            <div className={styles.inputGroup}>
              <label htmlFor="reviewText" className={styles.label}>
                Review:
              </label>
              <textarea
                id="reviewText"
                value={reviewText}
                onChange={(e) => setReviewText(e.target.value)}
                className={styles.textarea}
                placeholder="Edit your review here..."
                required
              />
            </div>

            <div className={styles.inputGroup}>
              <label htmlFor="rating" className={styles.label}>
                Rating:
              </label>
              <select
                id="rating"
                value={rating}
                onChange={(e) => setRating(Number(e.target.value))}
                className={styles.select}
                required
              >
                {[1, 2, 3, 4, 5].map((rate) => (
                  <option key={rate} value={rate}>
                    {rate}
                  </option>
                ))}
              </select>
            </div>

            {error && <div className={styles.error}>{error}</div>}

            <button type="submit" className={styles.button}>
              Update Review
            </button>
          </form>
        </div>
      )}
    </>
  );
}
