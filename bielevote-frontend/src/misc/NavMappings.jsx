import HomePage from "../page/home";
import CreateAccountPage from "../page/newAccount";
import ProjectWritingPage from "../page/project";
import NewsOverviewPage from "../page/news/NewsOverviewPage";
import NewsArticlePage from "../page/news/NewsArticlePage";
import ProjectOverviewPage from "../page/projectOverview/ProjectOverviewPage";
import ProjectPage from "../page/project/ProjectPage";
import RewardOverviewPage from "../page/rewardsShop/RewardOverviewPage";
import AccountSettingsPage from "../page/accountSettings/AccountSettingsPage";
import RewardsPurchased from "../page/rewardsShop/RewardsPurchased";
import AccountRequests from "../page/newAccount/requests/AccountRequests";

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
    path: "/new-account/requests",
    name: "Account Requests",
    allowedAccountTypes: [accountType.admin],
    hideFromMenu: false,
    element: () => <AccountRequests />,
  },
  {
    path: "/newproject",
    name: "New Project",
    allowedAccountTypes: [accountType.citizen, accountType.municipal],
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
  {
    path: "/projects/:projectId",
    name: "Project",
    allowedAccountTypes: allAccountTypes,
    hideFromMenu: true,
    element: () => <ProjectPage />,
  },
  {
    path: "/rewardsshop",
    name: "Rewards Shop",
    allowedAccountTypes: allAccountTypes,
    hideFromMenu: false,
    element: () => <RewardOverviewPage />,
  },
  {
    path: "/rewardspurchased",
    name: "Your orders",
    allowedAccountTypes: allLoggedInTypes,
    hideFromMenu: false,
    element: () => <RewardsPurchased />,
  },
  {
    path: "/settings",
    name: "Account Settings",
    allowedAccountTypes: allLoggedInTypes,
    hideFromMenu: false,
    element: () => <AccountSettingsPage />,
  },
];
