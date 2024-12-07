import "../types";
import { useState } from "react";
import { useRouter } from "next/router";
import Modal from "./modal";
import styles from "@/styles/Product.module.css";

export default function Product(product: product_t) {
    const { productID, productName, brand, price, quantity, rating, listingDate } = product;
    
    const router = useRouter();
    let [isModalOpen, setIsModalOpen] = useState<Boolean>(false);


    const openModal = () => setIsModalOpen(true);
    const closeModal = () => setIsModalOpen(false);

    const openDetails = () => {
        router.push(`/products/${productID}`);
    }

    return (
        <>
            <div className={styles.productCard}>
                <h2 className={styles.productTitle}>{productName}</h2>
                <p className={styles.productBrand}>Brand: <strong>{brand}</strong></p>
                <p className={styles.productPrice}>Price: <strong>${price.toFixed(2)}</strong></p>
                <p className={styles.productRating}>
                    Rating: <strong>{rating.toFixed(2)} / 5</strong>
                </p>
                <button className="button" onClick={() => openModal()}>Add to Cart</button>
                <button className={styles.detailsButton} onClick={() => openDetails()}>Details</button>
            </div>

            {isModalOpen && <Modal { ...{product, closeModal} } />}
        </>
    );
}