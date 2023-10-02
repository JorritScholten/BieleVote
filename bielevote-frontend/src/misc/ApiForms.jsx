export const projectStatus = {
  editing: "EDITING",
  proposed: "PROPOSED",
  denied: "DENIED",
  active: "ACTIVE",
  review: "REVIEW",
  accepted: "ACCEPTED",
  rejected: "REJECTED",
};

export const voteTypes = {
  for: "POSITIVE",
  neutral: "NEUTRAL",
  against: "AGAINST",
};

export const emptyForms = {
  login: {
    username: "",
    password: "",
  },
  newProject: {
    title: "",
    summary: "",
    content: "<p><br></p>",
    status: projectStatus.proposed,
  },
  project: {
    title: "",
    summary: "",
    content: "",
    author: "",
    datePublished: "",
    status: "",
  },
  projectInfoDTO: {
    title: "",
    summary: "",
    content: "",
    author: "",
    datePublished: "",
    status: "",
    votesFor: 0,
    votesNeutral: 0,
    votesAgainst: 0,
    progressPercentage: 0,
  },
  projectOverview: {
    projects: [],
    currentPage: 0,
    totalItems: 0,
    totalPages: 0,
  },
  newsArticle: {
    title: "",
    summary: "",
    content: "",
    author: "",
    datePlaced: "",
    category: "",
  },
  newsOverview: {
    articles: [],
    currentPage: 0,
    totalItems: 0,
    totalPages: 0,
  },
  rewardOverview: {
    rewards: [],
    currentPage: 0,
    totalItems: 0,
    totalPages: 0,
  },
  leaderboardPage: {
    scores: [],
    currentPage: 0,
    totalItems: 0,
    totalPages: 0,
  },
  rewardItem: {
    name: "",
    description: "",
    isLimited: false,
    inventory: 0,
    datePlaced: "",
    cost: 0,
  },
  createRewardDto: {
    name: "",
    description: "",
    isLimited: false,
    isAvailable: true,
    inventory: 0,
    cost: 0,
  },
  user: {
    username: "",
    legalName: "",
    phone: "",
    role: "",
    anonymousOnLeaderboard: false,
  },
  updateUsername: {
    newUsername: "",
  },
  rewardTransactionDto: {
    rewardId: -1,
    rewardsAmount: 1,
  },
  rewardTransactions: {
    transactions: [],
    currentPage: 0,
    totalItems: 0,
    totalPages: 0,
  },
  newAccount: {
    username: "",
    legalName: "",
    phone: "",
  },
};
