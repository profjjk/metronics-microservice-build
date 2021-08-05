import { useState, useEffect } from 'react';
import useJobs from '../hooks/useJobs';
import { SideNavbar, Searchbar, JobForm, JobsTable } from '../components';

const Jobs = () => {
  const { status, data, error, isFetching } = useJobs();
  const [searchTerm, setSearchTerm] = useState('');
  const [showForm, setShowForm] = useState(false);
  const [jobId, setJobId] = useState();

  switch (status) {
    case "loading":
      return <h1 className="text-center my-5">Loading</h1>;
    case "error":
      return <h4 className="text-center my-5">Error: {error.message}</h4>;
    default:
      return (
        <main>
          <SideNavbar />
          <div className="p-5">
            {showForm ? (
              ""
            ) : (
              <Searchbar
                heading="Service Job Search"
                subheading="Search by invoice #, date, or customer"
                placeholder="Invoice #, date, or customer"
                setSearch={setSearchTerm}
              />
            )}
            {showForm ? (
              <JobForm 
                setShowForm={setShowForm} 
                jobId={jobId} 
              />
            ) : (
              <JobsTable
                setShowForm={setShowForm}
                setJobId={setJobId}
                searchTerm={searchTerm}
                jobs={data.data}
              />
            )}
            {isFetching ? <p className="text-center my-5">Getting information from database...</p> : ""}
          </div>
        </main>
      );
  }
}

export default Jobs;
