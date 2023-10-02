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
  getProjectById,
  allowedToPostProject,
  deniedToPostProjectReasons,
  getAllNewsArticles,
  getNewsArticleById,
  getAllRewards,
  getRewardById,
  postReward,
  updateRewardInventory,
  updateRewardAvailability,
  getLeaderboard,
  getHasVoted,
  postVote,
  postRewardTransaction,
  getRewardTransactions,
  changeProjectStatus,
  updateUsername,
  toggleAnonymousOnLeaderboard,
  postAccountRequest,
  getAllAccountRequests,
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

function updateUsername(formData, user) {
  return instance.get("/api/v1/users/", formData, {
    headers: { Authorization: bearerAuth(user) },
  });
}

function getAccountBalance(user) {
  return instance.get("/api/v1/users/balance", {
    headers: { Authorization: bearerAuth(user) },
  });
}

function postAccountRequest(newAccount) {
  return instance.post("/api/v1/account-requests", newAccount, {
    headers: {
      "Content-type": "application/json",
    },
  });
}

function getAllAccountRequests(page, amount, user) {
  const path = "/api/v1/account-requests" + "?page=" + page + "&size=" + amount;
  if (user === null) {
    return instance.get(path);
  } else {
    return instance.get(path, {
      headers: {
        Authorization: bearerAuth(user),
      },
    });
  }
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

function getAllProjects(page, amount, statuses, user) {
  let filter = "";
  if (statuses.length !== 0) {
    filter = "&statusList=";
    statuses.forEach((s) => (filter = filter.concat(s, ",")));
    filter = filter.substring(0, filter.length - 1);
  }
  const path =
    "/api/v1/projects" + "?page=" + page + "&size=" + amount + filter;
  if (user === null) {
    return instance.get(path);
  } else {
    return instance.get(path, {
      headers: {
        Authorization: bearerAuth(user),
      },
    });
  }
}

function getProjectById(projectId, user) {
  const path = `/api/v1/projects/${projectId}`;
  if (user === null) {
    return instance.get(path);
  } else {
    return instance.get(path, {
      headers: {
        Authorization: bearerAuth(user),
      },
    });
  }
}

function getAllNewsArticles(page, amount) {
  return instance.get("/api/v1/news" + "?page=" + page + "&size=" + amount);
}

function getNewsArticleById(articleId) {
  return instance.get(`/api/v1/news/${articleId}`);
}

function getLeaderboard(timeRange, page, amount) {
  if (timeRange === null) {
    return instance.get(`/api/v1/leaderboard?page=${page}&amount=${amount}`);
  } else {
    return instance.get(`/api/v1/leaderboard?page=${page}&amount=${amount}`, {
      headers: {
        timeRange: timeRange,
      },
    });
  }
}

function getAllRewards(page, amount, user) {
  const path = "/api/v1/rewards/shop" + "?page=" + page + "&size=" + amount;
  if (user === null) {
    return instance.get(path);
  } else {
    return instance.get(path, {
      headers: {
        Authorization: bearerAuth(user),
      },
    });
  }
}

function getRewardById(rewardId, user) {
  const path = `/api/v1/rewards/shop/${rewardId}`;
  if (user === null) {
    return instance.get(path);
  } else {
    return instance.get(path, {
      headers: {
        Authorization: bearerAuth(user),
      },
    });
  }
}

function postReward(user, formData) {
  return instance.post("/api/v1/rewards/shop", formData, {
    headers: {
      Authorization: bearerAuth(user),
      "Content-type": "application/json",
    },
  });
}

function updateRewardInventory(updateInventory, rewardId, user) {
  return instance.patch(`/api/v1/rewards/shop/inventory/${rewardId}`, null, {
    headers: {
      Authorization: bearerAuth(user),
      newInventory: updateInventory,
    },
  });
}

function updateRewardAvailability(updateAvailability, rewardId, user) {
  return instance.patch(`/api/v1/rewards/shop/availability/${rewardId}`, null, {
    headers: {
      Authorization: bearerAuth(user),
      newAvailability: updateAvailability,
    },
  });
}

function postRewardTransaction(user, rewardData) {
  return instance.post("/api/v1/rewards/redeemed", rewardData, {
    headers: {
      Authorization: bearerAuth(user),
      "Content-type": "application/json",
    },
  });
}

function getRewardTransactions(user, page, amount) {
  return instance.get(
    "/api/v1/rewards/redeemed" + "?page=" + page + "&size=" + amount,
    {
      headers: { Authorization: bearerAuth(user) },
    }
  );
}

function getHasVoted(projectId, user) {
  return instance.get(`/api/v1/votes/${projectId}`, {
    headers: {
      Authorization: bearerAuth(user),
    },
  });
}

function allowedToPostProject(user) {
  return instance.get(`/api/v1/projects/allowed_to_post`, {
    headers: {
      Authorization: bearerAuth(user),
    },
  });
}

function deniedToPostProjectReasons(user) {
  return instance.get(`/api/v1/projects/allowed_to_post/reasons`, {
    headers: {
      Authorization: bearerAuth(user),
    },
  });
}

function postVote(voteType, projectId, user) {
  return instance.post(`/api/v1/votes/${projectId}`, null, {
    headers: {
      Authorization: bearerAuth(user),
      voteType: voteType,
    },
  });
}

function changeProjectStatus(newStatus, projectId, user) {
  return instance.patch(`/api/v1/projects/status/${projectId}`, null, {
    headers: {
      Authorization: bearerAuth(user),
      newStatus: newStatus,
    },
  });
}

function toggleAnonymousOnLeaderboard(user) {
  return instance.patch("/api/v1/users/update/anonymous", null, {
    headers: {
      Authorization: bearerAuth(user),
    },
  });
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
        window.location.href = "/";
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
