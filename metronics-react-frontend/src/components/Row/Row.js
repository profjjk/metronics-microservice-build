function Row(props) {
    return (
        <div className="row" style={props.style}>
            {props.children}
        </div>
    )
};

export default Row;