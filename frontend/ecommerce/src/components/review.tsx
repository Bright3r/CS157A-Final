import "../types";
import styles from "@/styles/Review.module.css";

export default function Review(props: review_t) {
    const { rating, comment, username, date } = props;

    return (
        <div className={styles.reviewCard}>
            <h2 className={styles.reviewTitle}>User Review</h2>
            <p className={styles.reviewDetail}>
                <span className={styles.label}>Username:</span> {username}
            </p>
            <p className={styles.reviewDetail}>
                <span className={styles.label}>Rating:</span> {rating.toFixed(2)} / 5
            </p>
            <p className={styles.reviewComment}>
                <span className={styles.label}>Comment:</span> {comment}
            </p>
            <p className={styles.reviewDetail}>
                <span className={styles.label}>Date:</span> {date.toDateString()}
            </p>
        </div>
    );
}