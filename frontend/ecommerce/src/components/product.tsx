import "../types";
import styles from "@/styles/Product.module.css";

export default function Product(
        { productID, productName, brand, price, quantity, rating, listingDate }: product_t) 
    {
    
    return (
        <div className={styles.productCard}>
            <h2 className={styles.productTitle}>{productName}</h2>
            <p className={styles.productBrand}>Brand: <strong>{brand}</strong></p>
            <p className={styles.productPrice}>Price: <strong>${price.toFixed(2)}</strong></p>
            <p className={styles.productRating}>
                Rating: <strong>{rating.toFixed(2)} / 5</strong>
            </p>
        </div>
    );
}