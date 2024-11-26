import "../types"


export default function Review(props: review_t) {
    const { rating, comment, username, date } = props;

    return (
        <>
            <h2>Review Component</h2>
            <p>{username}</p>
            <p>{rating}</p>
            <p>{comment}</p>
            <p>{date.toDateString()}</p>
        </>
    )
}