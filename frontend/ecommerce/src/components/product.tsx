import "../types"



export default function Product({ name, brand, price, rating }: product_t) {
    return (
        <>
            <h2>Product Component</h2>
            <p>{name}</p>
            <p>{brand}</p>
            <p>{price}</p>
            <p>{rating}</p>
        </>
    )
}