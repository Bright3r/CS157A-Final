

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
    password: string;
    address: address_t;
};

type product_t = {
    productID: number;
    productName: string;
    brand: string;
    price: number;
    quantity: number;
    rating: number;
    imageUrl: string;
    listingDate: string;
    category: string;
};  

type review_t = {
    reviewID: number;
    reviewComment: string;
    datePosted: Date;
    rating: number;
    product: product_t;
    user: user_t;
};

type order_t = {
    orderID: number;
    user: user_t;
    products: cart_product_t[];
    numProductsOrdered: number;
    dateOrdered: Date;
    shippingAddress: address_t;
    totalCost: number;
};

type cart_product_t = {
    product: product_t;
    quantityOrdered: number;
}