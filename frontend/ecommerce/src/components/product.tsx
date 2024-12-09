import "../types";
import { useState } from "react";
import { useRouter } from "next/router";
import Modal from "./modal";
import styles from "@/styles/Product.module.css";



export default function Product(props: product_t) {
  const {
    productID,
    productName,
    brand,
    price,
    rating,
    imageUrl
  } = props;

  const [isModalOpen, setIsModalOpen] = useState(false);
  const router = useRouter();

  const openModal = () => setIsModalOpen(true);
  const closeModal = () => setIsModalOpen(false);

  const openDetails = () => {
    router.push(`/products/${productID}`);
  };

  return (
    <>
      <div className={styles.productCard}>
        <img
          src={`http://localhost:8080/${imageUrl}`}
          alt={productName}
          className={styles.productImage}
        />
        <h2 className={styles.productTitle}>{productName}</h2>
        <p className={styles.productBrand}>
          Brand: <strong>{brand}</strong>
        </p>
        <p className={styles.productPrice}>
          Price: <strong>${price.toFixed(2)}</strong>
        </p>
        <p className={styles.productRating}>
          Rating: <strong>{rating.toFixed(2)} / 5</strong>
        </p>
        <button className="button" onClick={openModal}>
          Add to Cart
        </button>
        <button className={styles.detailsButton} onClick={openDetails}>
          Details
        </button>
      </div>

      {isModalOpen && (
        <Modal
          product={props}
          closeModal={closeModal}
        />
      )}
    </>
  );
}