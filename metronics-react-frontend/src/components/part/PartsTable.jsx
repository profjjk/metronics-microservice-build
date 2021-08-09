import { useEffect, useState } from 'react';
import { useMutation, useQueryClient } from 'react-query';
import API from '../../API';

const PartsTable = ({ searchTerm, parts }) => {
  const [partList, setPartList] = useState(parts)

  // Search for parts
  useEffect(() => {
    if (searchTerm === '') {
      setPartList(parts);
      return;
    }
    setPartList(parts.filter(part => {
      if (part.partNumber.toLowerCase().includes(searchTerm.toLowerCase())) {
        return true;
      } else if (part.description.toLowerCase().includes(searchTerm.toLowerCase())) {
        return true;
      } 
      return false;
    }))
  }, [searchTerm, parts]);

  // Mutations
  const queryClient = useQueryClient();
  const deletepart = useMutation(id => API.deletepart(id), {
    onSuccess: () => {
      queryClient.invalidateQueries('parts')
      console.log("part deleted!")
    }
  })

  // Event Handlers
  const viewHandler = e => {
    e.preventDefault();
    // setpartId(parseInt(e.target.dataset.id));
    // setShowFormUpdate(true);
  };
  const deleteHandler = async e => {
    e.preventDefault();
    await deletepart.mutate(parseInt(e.target.dataset.id))
  }

  return (
    <div className="mt-5">
      <h3 className="float-start">Inventory Search Results:</h3>
      <table className="table">
        <thead>
          <tr>
            <th scope="col">Part #</th>
            <th scope="col">Description</th>
            <th scope="col" className="text-center">Purchase $</th>
            <th scope="col" className="text-center">Sale $</th>
            <th scope="col" className="text-center">In Stock</th>
            <th scope="col" className="text-center">Change Quantity</th>
            <th scope="col"></th>
          </tr>
        </thead>
        <tbody>
          {partList.map((part) => (
            <tr key={part.id}>
              <td>{part.partNumber}</td>
              <td>{part.description}</td>
              <td className="text-center">{part.purchasePrice}</td>
              <td className="text-center">{part.salePrice}</td>
              <td className="text-center">{part.quantity}</td>
              <td className="d-flex justify-content-center">
                <button
                  className="btn btn-secondary"
                  data-id={part.id}
                  onClick={viewHandler}
                >
                  +
                </button>
                <button
                  className="btn btn-secondary ms-4"
                  data-id={part.id}
                  onClick={deleteHandler}
                >
                  -
                </button>
              </td>
              <td>
                <button
                  className="btn btn-warning ms-4"
                  data-id={part.id}
                  // onClick={editHandler}
                >
                  edit
                </button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
};

export default PartsTable;