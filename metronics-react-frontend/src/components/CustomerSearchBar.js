const CustomerSearchbar = ({ heading, subheading, placeholder, onChange, onClick }) => {
  return (
    <div id="search-bar">
      <h5 className="text-center">{heading}</h5>
      <p className="text-center">{subheading}</p>
      <input
        className="form-control text-center w-50 mx-auto"
        type="text"
        placeholder={placeholder}
        onChange={onChange}
      />
      <button
        type="button"
        className="btn btn-secondary position-relative top-10 start-50 translate-middle-x "
        onClick={onClick}
      >
        Search
      </button>
    </div>
  );
};

export default CustomerSearchbar;
