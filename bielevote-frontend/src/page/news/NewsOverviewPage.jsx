import { useState, useEffect } from "react";
import { Pagination } from "semantic-ui-react";

import { Header } from "../../components";
import { backendApi, handleLogError } from "../../misc/ApiMappings";
import ListNews from "./components/ListNews";
import { emptyForms } from "../../misc/ApiForms";

export default function NewsOverviewPage() {
  const [newsList, setNewsList] = useState(emptyForms.newsOverview);
  const amountOfArticles = 3;

  useEffect(() => {
    handlePageChange();
  }, []);

  const handlePageChange = async (event, value) => {
    const page = value !== null ? value.activePage - 1 : 0;
    try {
      const response = await backendApi.getAllNewsArticles(
        page,
        amountOfArticles
      );
      setNewsList(response.data);
    } catch (error) {
      handleLogError(error);
    }
  };

  return (
    <div className="flex flex-col gap-2 w-screen">
      <Header pageTitle="News" />
      <div className="flex flex-col items-center justify-center">
        <ListNews newsList={newsList} />
        <div className="">
          <Pagination
            defaultActivePage={1}
            totalPages={newsList.totalPages}
            onPageChange={handlePageChange}
          />
        </div>
      </div>
    </div>
  );
}
