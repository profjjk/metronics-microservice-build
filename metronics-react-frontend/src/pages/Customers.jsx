import { useQueryClient } from 'react-query';
import { useState } from 'react';
import useCustomers from '../hooks/useCustomers';
import { SideNavbar, Searchbar, CustomerTable } from '../components';

const Customers = () => {
  const { status, data, error, isFetching } = useCustomers();
  const [searchTerm, setSearchTerm] = useState('');
  const [showNewCustomerForm, setShowNewCustomerForm] = useState(false);

  switch (status) {
    case "loading":
      return <h1 className="text-center my-5">Loading</h1>;
    case "error":
      return <h4 className="text-center my-5">Error: {error.message}</h4>;
    default:
      return (
        <main>
          <div className="p-5">
            <SideNavbar />
            {showNewCustomerForm ? (
              ""
            ) : (
              <Searchbar
                heading="Customer Search"
                subheading="Search by business name"
                placeholder="Business Name"
                setSearch={setSearchTerm}
              />
            )}
            {!showNewCustomerForm ? (
              <CustomerTable
                // setShowFormUpdate={setShowFormUpdate}
                // setJobId={setJobId}
                // searchTerm={searchTerm}
                customers={data.data}
              />
            ) : (
              ""
            )}
            {isFetching ? (
              <p className="text-center my-5">
                Getting information from database...
              </p>
            ) : (
              ""
            )}
          </div>
        </main>
      );
  }
}

export default Customers;