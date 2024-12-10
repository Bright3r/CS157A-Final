import "../types";
import styles from "@/styles/Review.module.css";

interface ReviewProps {
  review: review_t;
  refresh: () => void;
}

export default function Review(props: ReviewProps) {
  const { reviewComment, rating, datePosted, user: reviewUser } = props.review;

  return (
    <div className={styles.reviewCard}>
      <h2 className={styles.reviewTitle}>User Review</h2>
      <p className={styles.reviewDetail}>
        <span className={styles.label}>Username:</span> {reviewUser.userName}
      </p>
      <p className={styles.reviewDetail}>
        <span className={styles.label}>Rating:</span> {rating.toFixed(0)} / 5
      </p>
      <p className={styles.reviewComment}>
        <span className={styles.label}>Comment:</span> {reviewComment}
      </p>
      <p className={styles.reviewDetail}>
        <span className={styles.label}>Date:</span>{" "}
        {new Date(datePosted).toLocaleString()}
      </p>
    </div>
  );
}
