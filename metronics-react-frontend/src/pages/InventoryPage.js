import React, { useEffect, useState } from "react";
import API from "../API/index";
import { useMutation, useQuery, useQueries, useQueryClient } from "react-query";
import ky from "ky";

import { SinglePartView, Searchbar, SideNavBar } from "../components";
import { set } from "js-cookie";

export default function InventoryPage() {
  const qc = useQueryClient();
  const { isLoading, data } = useQuery("fetchParts", API.getAllParts, {
    onSuccess: (data) => {
      // console.log(data);
    },
  });
  const [viewingSinglePart, setViewingSinglePart] = useState(false);
  const [singlePartInfo, setSinglePartInfo] = useState({});
  const [searchTerm, setSearchTerm] = useState("");

  // useEffect(async () => {
  // const response = await API.loggedIn("bob", "password");

  // const result = await API.updatePart({
  //   id: 12,
  //   description: "just an ordinary part",
  //   partNumber: "32332",
  //   quantity: 45,
  // });
  // }, []);

  async function switchToSinglePartView(part) {
    await setSinglePartInfo(part);
    await setViewingSinglePart(true);
  }

  function returnToListView() {
    // setSinglePartInfo({});
    setViewingSinglePart(false);
  }

  async function submitChanges(partToChange) {
    await API.updatePart(partToChange);
    qc.invalidateQueries("fetchParts");
    setViewingSinglePart(false);
  }
  const incrementStockMutation = useMutation(
    async (input) => {
      await API.updatePart(input);
    },
    {
      onSuccess: async () => {
        await qc.invalidateQueries("fetchParts");
      },
    }
  );

  const onChangeAmount = async (part) => {
    await incrementStockMutation.mutateAsync(part);
  };

  return (
    <div className="main-section">
      <div className="inventory-page-wrapper container">
        <SideNavBar />
        <div className="row">
          <div className="col-sm inventory-search-bar-wrapper">
            <Searchbar
              heading="Inventory Search"
              subheading="Search by part number or description"
              placeholder="Part # or description"
              setSearch={setSearchTerm}
            >
              {" "}
            </Searchbar>
          </div>
        </div>
        {viewingSinglePart ? (
          <div className="row">
            <div className="col-sm">
              <SinglePartView
                part={singlePartInfo}
                returnToListView={returnToListView}
                submitChanges={submitChanges}
              ></SinglePartView>
            </div>
          </div>
        ) : (
          <div className="row">
            <div className="col-sm">
              <table className="table table-sm">
                <thead>
                  <tr>
                    <th>Part #</th>
                    <th>Description</th>
                    <th style={{ textAlign: "center" }}># In Inventory</th>
                  </tr>
                </thead>
                <tbody>
                  {isLoading ? (
                    <tr>Loading...</tr>
                  ) : (
                    data
                      .filter((part) => {
                        if (part.description != null) {
                          if (
                            part.partNumber.includes(searchTerm) ||
                            part.description.includes(searchTerm)
                          ) {
                            return true;
                          } else {
                            return false;
                          }
                        } else if (
                          part.description === null &&
                          searchTerm === ""
                        ) {
                          return true;
                        } else {
                          return false;
                        }
                      })
                      .map((part) => {
                        return (
                          <tr key={part.id}>
                            <td> {part.partNumber} </td>
                            <td> {part.description} </td>
                            <td style={{ textAlign: "center" }}>
                              {" "}
                              {part.quantity}{" "}
                            </td>
                            <td
                              className="button-row"
                              style={{ borderStyle: "none" }}
                            >
                              <button
                                type="button"
                                className="inventory-adjust-btn"
                                disabled={isLoading ? true : false}
                                onClick={() =>
                                  onChangeAmount({
                                    ...part,
                                    quantity: part.quantity + 1,
                                  })
                                }
                              >
                                ᐃ
                              </button>
                              <button
                                type="button"
                                className="inventory-adjust-btn"
                                onClick={() =>
                                  onChangeAmount({
                                    ...part,
                                    quantity: part.quantity - 1,
                                  })
                                }
                              >
                                ᐁ
                              </button>
                              <button
                                type="button"
                                className="inventory-edit-btn"
                                onClick={() => switchToSinglePartView(part)}
                              >
                                ✎
                              </button>
                            </td>
                          </tr>
                        );
                      })
                  )}
                </tbody>
              </table>
            </div>
          </div>
        )}
      </div>
    </div>
  );
}
