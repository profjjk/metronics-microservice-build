import { useRef } from "react";
import { useMutation, useQueryClient } from "react-query";
import usePart from '../../hooks/usePart';
import API from '../../API';

const PartFormUpdate = ({ setShowFormUpdate, partId }) => {
  const { status, data, error } = usePart(partId);

  // Capture form input for part
  let partNumber = useRef(''); let description = useRef(''); let purchasePrice = useRef(''); 
  let salePrice = useRef(''); let quantity = useRef('');

  // Mutations
  const queryClient = useQueryClient();
  const editPart = useMutation(part => API.updatePart(part), {
    onSuccess: () => {
      queryClient.invalidateQueries('parts')
      queryClient.invalidateQueries(['part', partId])
      console.log("Part updated!")
    }
  });

  // Event Handlers
  const handleSubmit = async e => {
    e.preventDefault();

    const partInfo = {
      id: partId,
      partNumber: partNumber.current.value, 
      description: description.current.value, 
      purchasePrice: parseFloat(purchasePrice.current.value), 
      salePrice: parseFloat(salePrice.current.value), 
      quantity: parseInt(quantity.current.value)
    }

    await editPart.mutate(partInfo);

    setShowFormUpdate(false);
  };

  switch (status) {
    case "loading":
      return <h4 className="text-center my-5">Loading</h4>;
    case "error":
      return <h4 className="text-center my-5">Error: {error.message}</h4>;
    default:
      return (
        <>
          <form className="p-5" onSubmit={handleSubmit}>
            <h1 className="text-primary text-center mb-5">Edit Part Form</h1>

            <div className="my-3">
              <div className="px-3">
                <h6>Part Information</h6>
                <input
                  type="text"
                  className="form-control"
                  name="partNumber"
                  placeholder={"part #"}
                  defaultValue={data.data.partNumber || ""}
                  ref={partNumber}
                />
                <input
                  type="text"
                  className="form-control my-2"
                  name="description"
                  placeholder={"description"}
                  defaultValue={data.data.description || ""}
                  ref={description}
                />
                <input
                  type="number"
                  className="form-control"
                  name="purchasePrice"
                  placeholder={"purchase $"}
                  defaultValue={data.data.purchasePrice || 0}
                  ref={purchasePrice}
                />
                <input
                  type="number"
                  className="form-control my-2"
                  name="salePrice"
                  placeholder={"sale $"}
                  defaultValue={data.data.salePrice || 0}
                  ref={salePrice}
                />
                <input
                  type="number"
                  className="form-control"
                  name="quantity"
                  placeholder={"# in stock"}
                  defaultValue={data.data.quantity || 0}
                  ref={quantity}
                />
              </div>
            </div>

            <div className="mt-4 px-3">
              <button className="btn btn-primary me-3 form-btn" type="submit">
                Update
              </button>
              <button
                className="btn btn-secondary form-btn"
                onClick={() => setShowFormUpdate(false)}
                >Cancel
              </button>
            </div>
          </form>
        </>
      );
  }
}

export default PartFormUpdate;
