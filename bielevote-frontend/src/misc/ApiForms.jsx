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
  project: {
    title: "",
    content: "",
    author: "",
    datePublished: "",
    status: "",
  },
  projectInfoDTO: {
    title: "",
    content: "",
    author: "",
    datePublished: "",
    status: "",
    votesFor: 0,
    votesNeutral: 0,
    votesAgainst: 0,
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
};
