const QuickActions = () => {

  return (
    <div className="my-5">
      <h5><strong>Quick Actions</strong></h5>
      <div id="quick-actions">
        <a href="/customers/addcustomer"><div><button type="button" className="btn btn-secondary quick-btn">New Customer</button></div></a>
        <div><button type="button" className="btn btn-secondary quick-btn">New Service Request</button></div>
        <a href="/inventory/addpart"><div><button type="button" className="btn btn-secondary quick-btn">Add to Inventory</button></div></a>
      </div>
    </div>
  )
}

export default QuickActions;