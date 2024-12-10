import { useEffect, useState } from "react";
import Product from "@/components/product";
import axios from "axios";
import styles from "./Products.module.css";

export default function Products() {
  const [products, setProducts] = useState<product_t[]>([]);
  const [searchQuery, setSearchQuery] = useState("");
  const [filterCategory, setFilterCategory] = useState<string | null>(null);
  const [filterPrice, setFilterPrice] = useState<"asc" | "desc" | null>(null);
  const [filterRating, setFilterRating] = useState<"asc" | "desc" | null>(null);
  
  useEffect(() => {
    const fetchProducts = async () => {
      try {
        let url = "http://localhost:8080/api/products/search-filter-sort/";

        // Check for search by name
        if (searchQuery) {
          url += `?productName=${searchQuery}`;

          if (filterCategory || filterPrice || filterRating) {
            url += "&";
          }
        }

        // Check for filter by category
        if (filterCategory) {
          if (!searchQuery) {
            url += "?"
          }

          url += `category=${filterCategory}`;
        }

        const res = await axios.get<product_t[]>(url);
        setProducts(res.data);
      } catch (err) {
        console.error("Error fetching products:", err);
      }
    };

    fetchProducts();
  }, [searchQuery, filterCategory, filterPrice, filterRating]);

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
    setFilterRating(e.target.value as "asc" | "desc");
  };

  const sortedProducts = [...products];

  if (filterPrice) {
    sortedProducts.sort((a, b) => {
      if (filterPrice === "asc") {
        return a.price - b.price;
      }
      return b.price - a.price;
    });
  }

  if (filterRating) {
    sortedProducts.sort((a, b) => {
      if (filterRating === "asc") {
        return a.rating - b.rating;
      }
      return b.rating - a.rating;
    });
  }

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
            <option value="electronics">Electronics</option>
            <option value="computers">Computers</option>
            <option value="gaming">Gaming</option>
            <option value="wearables">Wearables</option>
            <option value="headphones">Headphones</option>
            <option value="home appliances">Home Appliances</option>
            <option value="smartwatch">Smartwatch</option>
            <option value="storage">Storage</option>
            <option value="smart home">Smart Home</option>
            <option value="smartphones">Smartphones</option>
          </select>

          <select onChange={handlePriceFilter} className={styles.filterSelect}>
            <option value="">Sort by Price</option>
            <option value="asc">Price: Low to High</option>
            <option value="desc">Price: High to Low</option>
          </select>

          <select onChange={handleRatingFilter} className={styles.filterSelect}>
            <option value="">Sort by Rating</option>
            <option value="asc">Rating: Low to High</option>
            <option value="desc">Rating: High to Low</option>
          </select>
        </div>
      </div>

      <div className={styles.productList}>
        {sortedProducts.map((product) => (
          <Product key={product.productID} {...product} />
        ))}
      </div>
    </>
  );
}
