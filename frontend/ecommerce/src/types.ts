

type address_t = {
    addrID: number;
    country: string;
    state: string;
    city: string;
    street: string;
    houseNumber: string;
    zipcode: string;
};

type user_t = {
    userID: number;
    userName: string;
    email: string;
    phoneNumber: string;

    adress: address_t;
};

type product_t = {
    productID: number;
    productName: string;
    brand: string;
    price: number;
    quantity: number;
    rating: number;
    listingDate: string;
};  

type review_t = {
    reviewID: number;
    reviewComment: string;
    datePosted: Date;

    product: product_t;
    user: user_t;
};

type order_t = {
    orderID: number;
    numProductsOrdered: number;
    dateOrdered: Date;
    total: number;

    products: product_t[];
    shippingAddress: address_t;
    user: user_t;
};

type cart_product_t = {
    product: product_t;
    purchaseQuantity: number;
}