import axios from "axios";

export const timeRanges = {
  allTime: "ALL_TIME",
  lastDay: "LAST_DAY",
  lastWeek: "LAST_WEEK",
  lastMonth: "LAST_MONTH",
  lastYear: "LAST_YEAR",
};

export const backendApi = {
  userInfo,
  getAccountBalance,
  login,
  authTest,
  postProject,
  getAllProjects,
  getAllNewsArticles,
  getNewsArticleById,
  getAllRewards,
  getRewardById,
  getLeaderboard,
};

function login(formData) {
  return instance.post("/auth/login", formData, {
    headers: { "Content-type": "application/json" },
  });
}

function userInfo(user) {
  return instance.get("/api/v1/users/me", {
    headers: { Authorization: bearerAuth(user) },
  });
}

function getAccountBalance(user) {
  return instance.get("/api/v1/users/balance", {
    headers: { Authorization: bearerAuth(user) },
  });
}

function authTest() {
  return instance.get("/auth/test");
}

function postProject(user, formData) {
  return instance.post("/api/v1/projects", formData, {
    headers: {
      Authorization: bearerAuth(user),
      "Content-type": "application/json",
    },
  });
}

function getAllProjects(page, amount) {
  return instance.get("/api/v1/projects" + "?page=" + page + "&size=" + amount);
}

function getAllNewsArticles(page, amount) {
  return instance.get("/api/v1/news" + "?page=" + page + "&size=" + amount);
}

function getNewsArticleById(articleId) {
  return instance.get(`/api/v1/news/${articleId}`);
}

function getLeaderboard(timeRange) {
  if (timeRange === null) {
    return instance.get("/api/v1/leaderboard");
  } else {
    return instance.get("/api/v1/leaderboard", {
      headers: {
        timeRange: timeRange,
      },
    });
  }
}

function getAllRewards(page, amount) {
  return instance.get("/api/v1/rewards" + "?page=" + page + "&size=" + amount);
}

function getRewardById(rewardId) {
  return instance.get(`/api/v1/rewards/${rewardId}`);
}
// -- Axios calls

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
