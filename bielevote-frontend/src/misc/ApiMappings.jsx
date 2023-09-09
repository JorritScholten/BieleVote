import axios from "axios";

export const backendApi = {
  login,
  authTest,
};

function login(formData) {
  console.log("attempting login with:");
  console.log(formData);
}

function authTest() {
  return instance.get("/auth/test");
}

// Axios calls

const instance = axios.create({
  baseURL: "http://localhost:8080",
});
