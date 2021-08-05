import { useState } from 'react';
import { SideNavbar, Searchbar, JobForm, JobsTable } from '../components';

const Jobs = () => {
  const [search, setSearch] = useState('');
  const [showForm, setShowForm] = useState(false);
  const [jobId, setJobId] = useState();

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
            setSearch={setSearch}
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
            search={search}
          />
        )}
      </div>
    </main>
  );
}

export default Jobs;
