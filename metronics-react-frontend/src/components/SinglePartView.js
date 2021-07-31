import React, { useState } from 'react'

export default function SinglePartView({ part, returnToListView, submitChanges }) {

	const [currentPart, setCurrentpart] = useState(part);




	return (
		<div className="single-part-view row">
			<div className="col-3"></div>
			<div className="col-6">
				<div className="return-to-list-view-btn-wrapper">
					<span className="return-to-list-view-btn" onClick={returnToListView}>ᐊ Return to List View</span>
				</div>
				<div className="part-input-wrapper">
					<span>Part Number</span><br />
					<input type="text" name="Part Number" value={currentPart.partNumber} onChange={(e) => setCurrentpart({...currentPart,partNumber:e.target.value})}></input>
				</div>
				<div className="part-input-wrapper">
					<span>Description</span><br />
					<input type="text" className="description-text-input" name="description" value={currentPart.description} onChange={e => setCurrentpart({...currentPart, description:e.target.value})}></input>

				</div>
				<div className="part-input-wrapper">
					<span>Purchase Price</span><br />
					<input type="text" class="" value={currentPart.purchasePrice} onChange={e => setCurrentpart({...currentPart, purchasePrice:e.target.value})}></input>
				</div>
				<div className="part-input-wrapper">
					<span>Sale Price</span><br />
					<input type="text" class="" value={currentPart.salePrice} onChange={e => setCurrentpart({...currentPart, salePrice:e.target.value})}></input>
				</div>
				<div className="part-input-wrapper">
					<span>Quantity</span><br />
					<input type="text" class="" value={currentPart.quantity} onChange={e => setCurrentpart({...currentPart, quantity:e.target.value})}></input>
				</div>

				<button onClick={() => submitChanges(currentPart)}>Save Changes</button>
			</div>
			<div className="col-3"></div>
		</div>
	)
}
