import React, { useState } from 'react';
import { SideNavBar } from "../components";
import API from "../API";


export default function InventoryAddPart() {
	const [currentPart, setCurrentpart] = useState({
		partNumber: "",
		description: "",
		purchasePrice: 0.00,
		salePrice: 0.00,
		quantity: 0
	});
	const [apiResponseStatus, setApiResponseStatus] = useState('noShow')

	async function submitNewPart() {
		try {
			await API.createPart(currentPart);
			setApiResponseStatus('sucess')

		} catch (err) {
			console.log(err)
			setApiResponseStatus('error')
		}

	}

	function errorOrSucess() {
		switch (apiResponseStatus) {
			case 'error':
				return <div className="add-part-error">Something Went Wrong!</div>
			case 'sucess':
				return <div className="add-part-sucess"><span>Part Uploaded! </span> <a href="/inventory/addpart">Click here to add another part</a></div>
		}


	}

	return (
		<div className="main-section">
			<div clasName="inventory-add-part container">
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
								<h1 style={{ marginTop: "3rem" }}> Add a new Part to Inventory</h1>
								<hr style={{ marginTop: "0px" }} />
								<div className="part-input-wrapper">
									<span>Part Number <span className={currentPart.partNumber ? "hidden" : "mandatory"}>- mandatory</span></span><br />
									<input type="text" name="Part Number" value={currentPart.partNumber} onChange={(e) => setCurrentpart({ ...currentPart, partNumber: e.target.value })}></input>
								</div>
								<div className="part-input-wrapper">
									<span>Description <span className={currentPart.description ? "hidden" : "mandatory"}>- mandatory</span ></span><br />
									<input type="text" className="description-text-input" name="description" value={currentPart.description} onChange={e => setCurrentpart({ ...currentPart, description: e.target.value })}></input>

								</div>
								<div className="part-input-wrapper">
									<span>Purchase Price</span><br />
									<input type="text" class="" value={currentPart.purchasePrice} onChange={e => setCurrentpart({ ...currentPart, purchasePrice: e.target.value })}></input>
								</div>
								<div className="part-input-wrapper">
									<span>Sale Price</span><br />
									<input type="text" class="" value={currentPart.salePrice} onChange={e => setCurrentpart({ ...currentPart, salePrice: e.target.value })}></input>
								</div>
								<div className="part-input-wrapper">
									<span>Quantity</span><br />
									<input type="text" class="" value={currentPart.quantity} onChange={e => setCurrentpart({ ...currentPart, quantity: e.target.value })}></input>
								</div>

								<button onClick={submitNewPart} disabled={!(currentPart.partNumber && currentPart.description)}>Add New Part</button>
								{
									errorOrSucess()
								}
							</div>
							<div className="col-3"></div>
						</div>

					</div>
				</div>


			</div>
		</div>
	)
}
