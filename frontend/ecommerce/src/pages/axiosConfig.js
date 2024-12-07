import axios from "axios";

const instance = axios.create({
  baseURL: "http://localhost:8080", // Backend URL
});

export default instance;
