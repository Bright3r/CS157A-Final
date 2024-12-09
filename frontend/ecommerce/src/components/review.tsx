import "../types";
import { useUser } from "@/context/UserContext";
import styles from "@/styles/Review.module.css";

export default function Review(props: review_t) {
    const { reviewID, reviewComment,rating, datePosted, product, user: reviewUser } = props;
    const { user } = useUser();

    const handleDelete = () => {

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