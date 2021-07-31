import ky from 'ky';
import Cookies from 'js-cookie';
import axios from "axios";

const customerUrl = 'http://localhost:5557/api/customer/';
const requestUrl = 'http://localhost:5557/api/requests/';
const partUrl = 'http://localhost:5557/api/part/';
const loginUrl = 'http://localhost:5557/api/loggedIn/';
const viewModelUrl = 'http://localhost:5557/viewmodel/';


const API = {

  //View model

  getViewModel() {
    return axios.get(viewModelUrl, { ...this.options });
  },

  // Customers
  getAllCustomers() {
    return ky.get(customerUrl, { ...this.options }).json();
  },
  getCustomerById(id) {
    return ky.get(customerUrl + id, { ...this.options }).json();
  },
  createCustomer(customer) {
    return ky.post(customerUrl, { json: customer, ...this.options }).json();
  },
  updateCustomer(customer, id) {
    return ky.put(customerUrl + id, { json: customer, ...this.options }).json();
  },
  deleteCustomer(customer, id) {
    return ky.delete(customerUrl + id, { json: customer, ...this.options }).json();
  },

  // Requests
  getAllRequests() {
    return ky.get(requestUrl, { ...this.options }).json();
  },
  getRequestById(id) {
    return ky.get(requestUrl + id, { ...this.options }).json();
  },
  createRequest(request) {
    return ky.post(requestUrl, { json: request, ...this.options }).json();
  },
  findByStatus(status) {
    return ky.get(requestUrl + 'status/' + status, { ...this.options }).json();
  },
  updateRequest(request) {
    console.log({...this.options, json: request })
    return ky.put(requestUrl, { json: request, ...this.options }).json();
  },
  deleteRequest(id,) {
    return ky.delete(requestUrl + id, { ...this.options });
  },

  // Parts
  getAllParts() {
    return ky.get(partUrl, { ...this.options }).json();
  },
  getPartById(id) {
    return ky.get(partUrl + id, { ...this.options }).json();
  },
  createPart(part) {
    return ky.post(partUrl, { json: part, ...this.options }).json();
  },
  updatePart(part) {
    console.log({...this.options, json:part})
    return ky.put(partUrl, { ...this.options, json:part });
  },
  deletePart(id) {
    return ky.delete(partUrl + id, { ...this.options });
  },
  async loggedIn(username, password) {
    const authToken = btoa(`${username}:${password}`);
    const response = await fetch('http://localhost:5557/api/loggedIn', {
      headers: {
        'Authorization': `Basic ${authToken}`
      },
      credentials: "include"
    })


  },
  options: {
    headers: {
      'X-XSRF-TOKEN': Cookies.get('XSRF-TOKEN')
    },
    credentials: 'include'
  }
}

export default API;