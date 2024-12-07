import "../types";
import { useCart } from "../context/CartContext";
import styles from "@/styles/CartProduct.module.css";

export default function CartProduct({ product, purchaseQuantity }: cart_product_t) {
    const { productID, productName, brand, price, rating, listingDate } = product;
    const { addCartProduct, removeCartProduct } = useCart();

    const handleRemoveFromCart = () => {
        removeCartProduct(productID);
    };

    return (
        <div className={styles.productCard}>
            <h2 className={styles.productTitle}>{productName}</h2>
            <p className={styles.productBrand}>Brand: <strong>{brand}</strong></p>
            <p className={styles.productPrice}>Price: <strong>${price.toFixed(2)}</strong></p>
            <p className={styles.productRating}>
                Rating: <strong>{rating.toFixed(2)} / 5</strong>
            </p>

            <div className={styles.purchaseDetails}>
                <p className={styles.purchaseQuantity}>Purchase Qty: <strong>{purchaseQuantity}</strong></p>
                <p className={styles.total}>Total: <strong>${(purchaseQuantity * price).toFixed(2)}</strong></p>
            </div>
            <button className="button" onClick={() => handleRemoveFromCart()}>Remove</button>
        </div>
    );
}