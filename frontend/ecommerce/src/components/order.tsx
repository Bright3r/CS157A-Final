import "../types"


export default function Order(props: order_t) {
    const { name, brand, price, rating } = props.product;

    return (
        <>
            <h2>Order Component</h2>
            <p>{name}</p>
            <p>{brand}</p>
            <p>{price}</p>
            <p>{rating}</p>
            <p>{props.orderDate.toDateString()}</p>
        </>
    )
}