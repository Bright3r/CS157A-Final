import "../types";
import styles from "@/styles/Product.module.css";
import { useCart } from "../context/CartContext";

export default function Product(product: product_t) {
    const { productID, productName, brand, price, quantity, rating, listingDate } = product;
    const { getCartProducts, addCartProduct } = useCart();

    const handleAddToCart = () => {
        const cartProduct: cart_product_t = { product, purchaseQuantity: 1 };
        addCartProduct(cartProduct);
    }

    return (
        <div className={styles.productCard}>
            <h2 className={styles.productTitle}>{productName}</h2>
            <p className={styles.productBrand}>Brand: <strong>{brand}</strong></p>
            <p className={styles.productPrice}>Price: <strong>${price.toFixed(2)}</strong></p>
            <p className={styles.productRating}>
                Rating: <strong>{rating.toFixed(2)} / 5</strong>
            </p>
            <button className="button" onClick={() => handleAddToCart()}>Add to Cart</button>
        </div>
    );
}