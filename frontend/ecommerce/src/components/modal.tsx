import "../types";
import { useCart } from "../context/CartContext";
import { useState } from "react";
import styles from "@/styles/Modal.module.css";

// Define ModalProps interface
interface ModalProps {
  product: product_t;
  closeModal: () => void;
}

export default function Modal({ product, closeModal }: ModalProps) {
  const {
    productID,
    productName,
    brand,
    price,
    quantity,
    rating,
    listingDate,
  } = product;

  // State for purchase quantity
  const [purchaseQuantity, setPurchaseQuantity] = useState<number>(1);
  const { addCartProduct } = useCart();

  // Handle adding product to cart
  const handleAddToCart = () => {
    if (purchaseQuantity > 0 && purchaseQuantity <= quantity) {
      const cartProduct: cart_product_t = { 
        product, 
        quantityOrdered: purchaseQuantity,
      };
      addCartProduct(cartProduct);
      closeModal();
    } else {
      alert("Invalid quantity selected.");
    }
  };

  // Handle quantity changes
  const handleUpdateQuantity = (newQuantity: number) => {
    if (newQuantity >= 0 && newQuantity <= quantity) {
      setPurchaseQuantity(newQuantity);
    }
  };

  return (
    <div className={styles.modalOverlay} onClick={closeModal}>
      <div
        className={styles.modalContent}
        onClick={(e) => e.stopPropagation()} // Prevent closing when clicking inside modal
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
              min="1"
              max={quantity}
              value={purchaseQuantity}
              onChange={(e) => handleUpdateQuantity(Number(e.target.value))}
            />
          </div>
          <p className={styles.purchaseDetail}>
            Total: <strong>${(purchaseQuantity * price).toFixed(2)}</strong>
          </p>
        </div>

        <div className={styles.buttons}>
          <button
            className={styles.buyButton}
            onClick={handleAddToCart}
            disabled={purchaseQuantity <= 0 || purchaseQuantity > quantity}
          >
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
