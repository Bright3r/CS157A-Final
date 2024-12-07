import { createContext, useContext, useState } from "react";

interface CartContextType {
    cartProducts: cart_product_t[];
    addCartProduct: (cartProduct: cart_product_t) => void;
    removeCartProduct: (productID: number) => void;
}

const CartContext = createContext<CartContextType | null>(null);

export const useCart = () => {
    const context = useContext(CartContext);
    if (!context) {
        throw new Error("useCart must be used within a CartProvider");
    }
    return context;
}

export const CartProvider: React.FC<{ children: React.ReactNode }> = ({ children }) => {
    const [cartProducts, setCartProducts] = useState<cart_product_t[]>([]);

    const addCartProduct = (newCartProduct: cart_product_t) => {
        const newCart = cartProducts;

        let alreadyInCart = false;
        newCart.forEach((cartProduct) => {
            if (cartProduct.product.productID === newCartProduct.product.productID) {
                cartProduct.purchaseQuantity++;
                alreadyInCart = true;
            }
        })

        if (!alreadyInCart) {
            newCart.push(newCartProduct);
        }
        setCartProducts(newCart);
    }

    const removeCartProduct = (productID: number) => {
        const newProducts = cartProducts.filter((cartProduct) => cartProduct.product.productID !== productID);
        setCartProducts(newProducts);
    }

    return (
        <CartContext.Provider 
            value={{ cartProducts, addCartProduct, removeCartProduct }}
        >
            {children}
        </CartContext.Provider>
    );
}