import HomePage from "../page/home";
import CreateAccountPage from "../page/newAccount";
import ProjectWritingPage from "../page/project";
import NewsOverviewPage from "../page/news/NewsOverviewPage";
import NewsArticlePage from "../page/news/NewsArticlePage";
import ProjectOverviewPage from "../page/projectOverview/ProjectOverviewPage";

// these are the Enum options of UserRole as defined in the backend, except the visitor type which is used to represent no account/anonymous visitor
export const accountType = {
  admin: "ADMINISTRATOR",
  municipal: "MUNICIPAL",
  citizen: "CITIZEN",
  visitor: "VISITOR",
};
const municipalTypes = [accountType.admin, accountType.municipal];
const allLoggedInTypes = municipalTypes.concat(accountType.citizen);
const allAccountTypes = allLoggedInTypes.concat(accountType.visitor);

export const navList = [
  {
    path: "/",
    name: "Home",
    allowedAccountTypes: allAccountTypes,
    hideFromMenu: false,
    element: () => <HomePage />,
  },
  {
    path: "/news",
    name: "News",
    allowedAccountTypes: allAccountTypes,
    hideFromMenu: false,
    element: () => <NewsOverviewPage />,
  },
  {
    path: "/news/:articleId",
    name: "Article",
    allowedAccountTypes: allAccountTypes,
    hideFromMenu: true,
    element: () => <NewsArticlePage />,
  },
  {
    path: "/new-account",
    name: "New account",
    allowedAccountTypes: allAccountTypes,
    hideFromMenu: true,
    element: () => <CreateAccountPage />,
  },
  {
    path: "/projectwritingpage",
    name: "New Project",
    allowedAccountTypes: allLoggedInTypes,
    hideFromMenu: false,
    element: () => <ProjectWritingPage />,
  },
  {
    path: "/projects",
    name: "Projects",
    allowedAccountTypes: allAccountTypes,
    hideFromMenu: false,
    element: () => <ProjectOverviewPage />,
  },
];
