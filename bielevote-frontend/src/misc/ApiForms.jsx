export const projectStatus = {
  editing: "EDITING",
  proposed: "PROPOSED",
  active: "ACTIVE",
  accepted: "ACCEPTED",
  rejected: "REJECTED",
  archived: "OLD",
};

export const emptyForms = {
  login: {
    username: "",
    password: "",
  },
  newProject: {
    title: "",
    content: "<p><br></p>",
    status: projectStatus.proposed,
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
  rewardItem: {
    name: "",
    description: "",
    isLimited: false,
    inventory: 0,
    datePlaced: "",
    cost: 0,
  },
  user: {
    username: "",
    legalName: "",
    phone: "",
    role: "",
  },
  updateUsername: {
    newUsername: "",
  },
};
