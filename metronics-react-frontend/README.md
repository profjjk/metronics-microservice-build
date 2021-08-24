# Metronics React Frontend

## Code Examples
Custom hooks for fetching data using react-query:
```javascript
const fetchJobs = async () => {
  const jobs = await axios.get('http://localhost:5557/api/jobs/');
  return jobs;
}
export default function useJobs() {
  return useQuery('jobs', () => fetchJobs());
}
```
Handling asynchronous queries:
```javascript
  const handleSubmit = async e => {
    e.preventDefault();
    if (customerExists) {
      await editCustomer.mutate({ id: customer[0].id, ...customerInfo });
      await createJob.mutate({ customerId: customer[0].id, ...jobInfo });
    } else {
      let newCustomer = await createCustomer.mutate(customerInfo)
      await createJob.mutate({ customerId: newCustomer.id, ...jobInfo })
    }
    setShowFormNew(false);
  };
```