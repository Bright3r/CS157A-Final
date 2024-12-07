import "../types";
import { useCart } from "../context/CartContext";
import { useState } from "react";
import styles from "@/styles/Modal.module.css";

interface ModalProps {
    product: product_t;
    closeModal: () => void;
}

export default function Modal({ product, closeModal }: ModalProps) {
    const { productID, productName, brand, price, quantity, rating, listingDate } = product;
    
    let [purchaseQuantity, setPurchaseQuantity] = useState<number>(1);
    const { addCartProduct } = useCart();


    const handleAddToCart = () => {
        const cartProduct: cart_product_t = { product, purchaseQuantity };
        addCartProduct(cartProduct);
        closeModal();
    }

    const handleUpdateQuantity = (newQuantity: number) => {
        setPurchaseQuantity(newQuantity);
    }

    return (
        <div className={styles.modalOverlay} onClick={closeModal}>
            <div
                className={styles.modalContent}
                onClick={(e) => e.stopPropagation()}
            >
                <h2 className={styles.modalHeader}>Purchase</h2>
                <div className={styles.purchaseDetails}>
                    <p className={styles.purchaseDetail}>
                        Item: <strong>{productName}</strong>
                    </p>
                    <p className={styles.purchaseDetail}>
                        Brand: <strong>{brand}</strong>
                    </p>
                    <div className={styles.quantityContainer}>
                        <p className={styles.purchaseDetail}>Qty: </p>
                        <input
                            className={styles.purchaseQuantitySelector}
                            type="number"
                            id="quantity"
                            name="purchaseQuantity"
                            min="0"
                            max="99"
                            value={purchaseQuantity}
                            onChange={(e) => handleUpdateQuantity(Number(e.target.value))}
                        />
                    </div>
                    <p className={styles.purchaseDetail}>Total: <strong>${(purchaseQuantity * price).toFixed(2)}</strong></p>
                </div>

                <div className={styles.buttons}>
                    <button className={styles.buyButton} onClick={() => handleAddToCart()}>
                        Add
                    </button>
                    <button className={styles.closeModalButton} onClick={closeModal}>
                        Close
                    </button>
                </div>
            </div>
        </div>
    );
}