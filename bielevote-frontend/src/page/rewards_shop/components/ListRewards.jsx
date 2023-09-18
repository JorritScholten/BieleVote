import PropTypes from "prop-types";

export default function ListRewards(rewardsList) {
  return rewardsList.rewards == [] ? (
    <div>loading...</div>
  ) : (
    <div className="flex flex-col  w-3/5">
      {newsList.articles.map((articlePreview) => (
        <div className="p-3 flex flex-col" key={articlePreview.id}>
          <Link to={"/news/" + articlePreview.id}>
            <div className="text-3xl text-blue-700 font-bold underline">
              {articlePreview.title}
            </div>
          </Link>
          <div className="flex flex-row">
            <div className="mr-3 text-gray-600">
              {formatDate(articlePreview.datePlaced)}
            </div>
            <div className="flex items-center">
              <BsFillCalendarWeekFill />
            </div>
          </div>
          <div className="text-lg text-gray-800 font-semibold">
            {articlePreview.summaryPreview}
          </div>
        </div>
      ))}
    </div>
  );
}

ListRewards.propTypes = {
  newsList: PropTypes.object.isRequired,
};
