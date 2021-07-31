import { useEffect, useState } from "react";

const PartsWarning = ({ parts }) => {
  const [orderParts, setOrderParts] = useState([])

  useEffect(() => {
    let orderList = [];
    for (let part of parts) {
      if (part.quantity < 4) {
        orderList.push(part);
      }
    }
    setOrderParts(orderList);
  }, [parts])

  return (
    <div className="my-5">
      <h5><strong>Parts that need to be ordered</strong></h5>
      <table className="table">
        <thead>
          <tr>
            <th scope="col">Part #</th>
            <th scope="col">Description</th>
            <th scope="col"># In Inventory</th>
          </tr>
        </thead>
        <tbody>
          {orderParts.map(part => (
            <tr key={part.id}>
              <td>{part.partNumber}</td>
              <td>{part.description}</td>
              <td>{part.quantity}</td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  )
}

export default PartsWarning;