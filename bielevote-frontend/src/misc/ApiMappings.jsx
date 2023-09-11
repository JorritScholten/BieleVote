import axios from "axios";

export const backendApi = {
  userInfo,
  login,
  authTest,
};

function login(formData) {
  console.log("attempting login with:");
  console.log(formData);
  return instance.post("/auth/login", formData, {
    headers: { "Content-type": "application/json" },
  });
}

function userInfo(user) {
  return instance.get("/api/v1/users/me", {
    headers: { Authorization: bearerAuth(user) },
  });
}

function authTest() {
  return instance.get("/auth/test");
}

// Axios calls

const instance = axios.create({
  baseURL: "http://localhost:8080",
});

instance.interceptors.request.use(
  function (config) {
    // If token is expired, redirect user to login
    if (config.headers.Authorization) {
      const token = config.headers.Authorization.split(" ")[1];
      const data = parseJwt(token);
      if (Date.now() > data.exp * 1000) {
        window.location.href = "/login";
      }
    }
    return config;
  },
  function (error) {
    return Promise.reject(error);
  }
);

// -- Helper functions

function bearerAuth(user) {
  return `Bearer ${user.accessToken}`;
}

export function parseJwt(token) {
  if (!token) {
    return;
  }
  const base64Url = token.split(".")[1];
  const base64 = base64Url.replace("-", "+").replace("_", "/");
  return JSON.parse(window.atob(base64));
}

export const handleLogError = (error) => {
  if (error.response) {
    console.log(error.response.data);
  } else if (error.request) {
    console.log(error.request);
  } else {
    console.log(error.message);
  }
};
