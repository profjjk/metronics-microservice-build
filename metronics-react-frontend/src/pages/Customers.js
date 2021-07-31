/* eslint-disable react-hooks/exhaustive-deps */
import { useQuery, useQueryClient } from "react-query";
// import React, { useEffect, useState } from "react";
import { SideNavBar, CustomerSearchBar } from '../components';
import CustomersTable from "../components/CustomersTable";
import API from "../API";
import { useState } from "react";

const fetchCustomers = async () => await API.getAllCustomers();

function Customers() {
  const customers = useQuery("customers", fetchCustomers,{
    onSuccess: () =>{
      setSearchResults(customers.data)
    }
  });
  const [ data, setData ] = useState();
  const [ search, setSearch ] = useState();
  const [ searchResults, setSearchResults ] = useState(customers.data);

  console.log("==== CUSTOMER DATA ====");
  console.log(customers.data);
  console.log("==== SEARCH DATA ====");
  console.log(searchResults);

  const queryClient = useQueryClient();

  // useEffect(async ()=>{
  //   fetchCustomers();
  // }, []);

  const handleInputChange = (event) => {
    setSearch(event.target.value);
  };

  const handleSearch = async (event) => {
    event.preventDefault();

    const results = customers.data.filter((c) => {
      return (
        c.businessName.includes(search) ||
        c.contactName.includes(search) ||
        c.street.includes(search) ||
        c.city.includes(search) ||
        c.state.includes(search) ||
        c.zipcode.includes(search)||
        c.phone.includes(search)
      );
    });
    console.log(results);
    setSearchResults(results);
  };

  // useEffect(async ()=>{
  // const result = await API.createCustomer({
  //         businessName: "New",
  //         contactName: "Testing",
  //         phone: "123-456-7890",
  //         street: "Claremont ave.",
  //         city: "Montclair",
  //         state: "New Jersey",
  //         zipcode: "07042"
  // })
  //     console.log(result);
  // }, [])

  // const [customers, setCustomers] = useState([]);
  // const [search, setSearch] = useState([]);
  // const [searchResults, setSearchResults] = useState([]);

  // useEffect(() => {
  //     getCustomers();
  // }, []);

  // const getCustomers = () => {
  //     API.getAllCustomers()
  //     .then((res) => {
  //         setCustomers(res.data);
  //         console.log(customers);
  //     })
  //     .catch(err => console.log(err));
  // }
  switch (customers.status) {
    case "loading":
      return <h1 className="text-center my-5">Loading</h1>;
    case "error":
      return <h4 className="text-center my-5">Oops, something went wrong!</h4>;
    default:
      return (
        <>
          <SideNavBar />
          <div className="main-section">
            <CustomerSearchBar
              heading="Customer Search"
              subheading="Search by name, address, or phone number"
              placeholder="name, address, or phone number"
              onChange={handleInputChange}
              onClick={handleSearch}

            />

           <CustomersTable searchResults={searchResults || customers.data} />
          </div>
        </>
      );
  }
}

export default Customers;
