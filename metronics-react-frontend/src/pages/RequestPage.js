import { useQuery, useQueryClient, useMutation } from 'react-query';
import { SideNavBar, Searchbar, RequestTable, RequestForm } from '../components';
import API from '../API';
import { useState, useEffect } from 'react';

const fetchRequests = async () => await API.getAllRequests();


const RequestPage = () => {
  const requests = useQuery('requests', fetchRequests);
  const queryClient = useQueryClient();
  const requestList = queryClient.getQueryData('requests');
  console.log(requestList)

  const [search, setSearch] = useState('');
  const [searchResults, setSearchResults] = useState(requestList);
  const [showForm, setShowForm] = useState(false);
  const [selectedRequest, setSelectedRequest] = useState();

  useEffect(() => {
    if (search === '') {
      setSearchResults(requestList);
      return;
    }
    let searchRequestList = [];
    for (let request of requestList) {
      let name = request.customer.businessName.toLowerCase(); let invoice = request.invoiceNumber; let date = request.dateCompleted;
      if (name.indexOf(search) !== -1 || invoice.indexOf(search) !== -1 || date.indexOf(search) !== -1) {
        searchRequestList.push(request);
      }
    }
    setSearchResults(searchRequestList);
  }, [search]);

  switch (requests.status) {
    case 'loading':
      return <h1 className="text-center my-5">Loading</h1>;
    case 'error':
      return <h4 className="text-center my-5">Oops, something went wrong!</h4>
    default:
      return (
        <>
          <SideNavBar />
          <div className="main-section">
            {
              showForm ? "" :
              <Searchbar 
                heading="Service Request Search"
                subheading="Search by customer, date completed, or invoice #"
                placeholder="Customer, date, or invoice #"
                setSearch={setSearch} />
            }
            {
              showForm ?
              <RequestForm 
                setShowForm={setShowForm}
                selectedRequest={selectedRequest} /> :
              <RequestTable 
                searchResults={searchResults}
                setShowForm={setShowForm}
                setSelectedRequest={setSelectedRequest} />
            }
          </div>
        </>
      )
    
  }
}

export default RequestPage;