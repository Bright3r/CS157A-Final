import "../types";
import styles from "@/styles/Review.module.css";

export default function Review(props: review_t) {
    const { reviewID, reviewComment, datePosted, product, user } = props;

    return (
        <div className={styles.reviewCard}>
            <h2 className={styles.reviewTitle}>User Review</h2>
            <p className={styles.reviewDetail}>
                <span className={styles.label}>Username:</span> {user.userName}
            </p>
            <p className={styles.reviewDetail}>
                <span className={styles.label}>Rating:</span> {product.rating.toFixed(2)} / 5
            </p>
            <p className={styles.reviewComment}>
                <span className={styles.label}>Comment:</span> {reviewComment}
            </p>
            <p className={styles.reviewDetail}>
                <span className={styles.label}>Date:</span> {datePosted.toString()}
            </p>
        </div>
    );
}