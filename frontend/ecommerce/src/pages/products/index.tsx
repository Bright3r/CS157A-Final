import "../../types";
import { useEffect, useState } from "react";
import Product from "@/components/product";
import axios from "axios";
import styles from "./Products.module.css";

export default function Products() {
  const [products, setProducts] = useState<product_t[]>([]);
  const [searchQuery, setSearchQuery] = useState("");
  const [filterCategory, setFilterCategory] = useState<string | null>(null);
  const [filterPrice, setFilterPrice] = useState<"asc" | "desc" | null>(null);
  const [filterRating, setFilterRating] = useState<number | null>(null);

  useEffect(() => {
    const fetchProducts = async () => {
      try {
        const res = await axios.get<product_t[]>(
          "http://localhost:8080/api/products"
        );
        console.log(res);
        setProducts(res.data);
      } catch (err) {
        console.error("Error fetching products:", err);
      }
    };

    fetchProducts();
  }, []);

  const handleSearch = (e: React.ChangeEvent<HTMLInputElement>) => {
    setSearchQuery(e.target.value);
  };

  const handleCategoryFilter = (e: React.ChangeEvent<HTMLSelectElement>) => {
    setFilterCategory(e.target.value);
  };

  const handlePriceFilter = (e: React.ChangeEvent<HTMLSelectElement>) => {
    setFilterPrice(e.target.value as "asc" | "desc");
  };

  const handleRatingFilter = (e: React.ChangeEvent<HTMLSelectElement>) => {
    setFilterRating(Number(e.target.value));
  };

  const applyFilters = (products: product_t[]) => {
    return products
      .filter(
        (product) =>
          product.productName
            .toLowerCase()
            .includes(searchQuery.toLowerCase()) ||
          product.category.toLowerCase().includes(searchQuery.toLowerCase())
      )
      .filter((product) => {
        if (filterCategory && product.category !== filterCategory) return false;
        return true;
      })
      .filter((product) => {
        if (filterRating && product.rating < filterRating) return false;
        return true;
      })
      .sort((a, b) => {
        if (filterPrice === "asc") {
          return a.price - b.price;
        } else if (filterPrice === "desc") {
          return b.price - a.price;
        }
        return 0;
      });
  };

  return (
    <>
      <h1 className={styles.header}>Products</h1>

      <div className={styles.searchFilterContainer}>
        <input
          type="text"
          placeholder="Search by product name or category..."
          value={searchQuery}
          onChange={handleSearch}
          className={styles.searchInput}
        />

        <div className={styles.filters}>
          <select
            onChange={handleCategoryFilter}
            className={styles.filterSelect}
          >
            <option value="">Filter by Category</option>
          </select>

          <select onChange={handlePriceFilter} className={styles.filterSelect}>
            <option value="">Sort by Price</option>
            <option value="asc">Price: Low to High</option>
            <option value="desc">Price: High to Low</option>
          </select>

          <select onChange={handleRatingFilter} className={styles.filterSelect}>
            <option value="">Filter by Rating</option>
            <option value="4">4+ Stars</option>
            <option value="3">3+ Stars</option>
          </select>
        </div>
      </div>

      <div className={styles.productList}>
        {applyFilters(products).map((product) => (
          <Product key={product.productID} {...product} />
        ))}
      </div>
    </>
  );
}
