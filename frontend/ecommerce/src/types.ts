

type product_t = {
    name: string;
    brand: string;
    price: number;
    rating: number;
}

type order_t = {
    product: product_t;
    orderDate: Date;
}

type review_t = {
    rating: number;
    comment: string;
    username: string;
    date: Date;
}