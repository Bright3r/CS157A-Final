import "../types";
import { useUser } from "@/context/UserContext";
import styles from "@/styles/Review.module.css";
import axios from "axios";
import { useRouter } from "next/router";

export default function Review(props: review_t) {
    const { reviewID, reviewComment,rating, datePosted, product, user: reviewUser } = props;
    const { user } = useUser();
    const router = useRouter();

    const handleDelete = async () => {

        try {
            const res = await axios.delete(`http://localhost:8080/api/reviews/${reviewID}`);
            router.replace(router.asPath, undefined, { scroll: false });
        } catch (error) {
            console.error("Failed to delete review: ", error);
            alert("Failed to delete review");
        }
    }

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
                <span className={styles.label}>Date:</span> {datePosted.toString()}
            </p>
            {user && (user.userName === reviewUser.userName) &&
                <button className={styles.deleteButton} onClick={() => handleDelete()}>Delete</button>
            }
        </div>
    );
}