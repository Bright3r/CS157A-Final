import "../types";
import styles from "@/styles/Order.module.css";

export default function Order(
        { orderID, numProductsOrdered, dateOrdered, products, shippingAddress, user }: order_t) 
    {

    return (
        <div key={orderID} className={styles.orderCard}>
            <h2 className={styles.orderTitle}>Order Summary</h2>
            {products.map((cartItem) =>
                <>
                    <p className={styles.orderDetail}>
                        <span className={styles.label}>Product Name:</span> {cartItem.product.productName}
                    </p>
                    <p className={styles.orderDetail}>
                        <span className={styles.label}>Brand:</span> {cartItem.product.brand}
                    </p>
                    <p className={styles.orderDetail}>
                        <span className={styles.label}>Qty:</span> {cartItem.quantityOrdered}
                    </p>
                    <p className={styles.orderDetail}>
                        <span className={styles.label}>Price:</span> ${cartItem.product.price}
                    </p>
                </>
            )}
            <p className={styles.orderDetail}>
                <span className={styles.label}>Order Date:</span> {dateOrdered.toString()}
            </p>
            <p className={styles.orderDetail}>
                <span className={styles.label}>Total:</span> ${}
            </p>
        </div>
    );
}