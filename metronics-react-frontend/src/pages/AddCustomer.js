import React, { useState } from 'react';
import { SideNavBar } from "../components";
import API from "../API";

export default function AddCustomer() {
	const [currentCustomer, setCurrentCustomer] = useState({
		businessName: "",
		contactName: "",
		phone: "",
		street: "",
		city: "",
		state: "",
		zipcode: ""
	});
	const [apiResponseStatus, setApiResponseStatus] = useState('noShow')

	async function submitNewCustomer(){
		try{
			await API.createCustomer(currentCustomer);
			setApiResponseStatus('sucess')

		} catch(err){
			console.log(err)
			setApiResponseStatus('error')
		}

	}

	function errorOrSucess(){
		switch(apiResponseStatus){
			case 'error':
			return <div className="add-part-error">Something Went Wrong!</div>
			case 'sucess':
			return <div className="add-part-sucess"><span>Customer Uploaded! </span> <a href="/customers/addcustomer">Click here to add another Customer</a></div>
		}

		
	}
	return (
		<div className="main-section">
			<SideNavBar></SideNavBar>
			<div clasName="row">
				<div className="col-3"></div>
				<div className="col-6-sm">
				</div>
				<div className="col-3"></div>
			</div>
			<div className="row">
				<div className="col-sm">
					<div className="single-part-view row">
						<div className="col-3"></div>
						<div className="col-6">
							<h1 style={{ marginTop: "3rem" }}> Add a new customer</h1>
							<hr style={{ marginTop: "0px" }} />
							<div className="part-input-wrapper">
								<span>Business Name <span className={currentCustomer.businessName ? "hidden" : "mandatory"}>- mandatory</span></span><br />
								<input type="text" value={currentCustomer.businessName} onChange={(e) => setCurrentCustomer({ ...currentCustomer, businessName: e.target.value })}></input>
							</div>
							<div className="part-input-wrapper">
								<span>Contact Name </span><br />
								<input type="text" value={currentCustomer.contactName} onChange={(e) => setCurrentCustomer({ ...currentCustomer, contactName: e.target.value })}></input>
							</div>
							<div className="part-input-wrapper">
								<span>Phone <span className={currentCustomer.phone ? "hidden" : "mandatory"}>- mandatory</span></span><br />
								<input type="text" value={currentCustomer.phone} onChange={(e) => setCurrentCustomer({ ...currentCustomer, phone: e.target.value })}></input>
							</div>
							<div className="part-input-wrapper">
								<span>Street <span className={currentCustomer.street ? "hidden" : "mandatory"}>- mandatory</span></span><br />
								<input type="text" value={currentCustomer.street} onChange={(e) => setCurrentCustomer({ ...currentCustomer, street: e.target.value })}></input>
							</div>
							<div className="part-input-wrapper">
								<span>City <span className={currentCustomer.city ? "hidden" : "mandatory"}>- mandatory</span></span><br />
								<input type="text" value={currentCustomer.city} onChange={(e) => setCurrentCustomer({ ...currentCustomer, city: e.target.value })}></input>
							</div>
							<div className="part-input-wrapper">
								<span>State <span className={currentCustomer.state ? "hidden" : "mandatory"}>- mandatory</span></span><br />
								<input type="text" value={currentCustomer.state} onChange={(e) => setCurrentCustomer({ ...currentCustomer, state: e.target.value })}></input>
							</div>
							<div className="part-input-wrapper">
								<span>Zip Code <span className={currentCustomer.zipcode ? "hidden" : "mandatory"}>- mandatory</span></span><br />
								<input type="text" value={currentCustomer.zipcode} onChange={(e) => setCurrentCustomer({ ...currentCustomer, zipcode: e.target.value })}></input>
							</div>

							<button onClick={submitNewCustomer} disabled={!(currentCustomer.businessName && currentCustomer.phone && currentCustomer.state && currentCustomer.street && currentCustomer.city && currentCustomer.zipcode)}>
							Add New Part</button>
							{
								errorOrSucess()
							}
						</div>
						<div className="col-3"></div>
					</div>

				</div>
			</div>


		</div>
	)
}
