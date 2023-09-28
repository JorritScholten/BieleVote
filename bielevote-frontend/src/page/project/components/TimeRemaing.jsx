/* eslint-disable react/prop-types */
import { useEffect, useState } from "react";

export default function TimeRemaing(props) {
  const [timeRemaining, setTimeRemaining] = useState();

  console.table(props.project);
  console.log(props.project.endOfVoting + "PPV");
  useEffect(() => {
    console.log(props.project.endOfVoting + "PPV2");
    const endOfVoting = new Date(props.project.endOfVoting);
    var difference = endOfVoting - Date.now();
    console.log(props.project.endOfVoting);
    console.log(endOfVoting + typeof endOfVoting);

    console.log(difference);
    setTimeRemaining({
      days: Math.floor(difference / (1000 * 60 * 60 * 24)),
      hours: Math.floor((difference / (1000 * 60 * 60)) % 24),
      minutes: Math.floor((difference / 1000 / 60) % 60),
      seconds: Math.floor((difference / 1000) % 60),
    });
  }, [props.project.endOfVoting]);

  return (
    <div>
      {timeRemaining ? (
        <div>{timeRemaining.days} days {timeRemaining.hours} hours {timeRemaining.minutes} minutes {timeRemaining.seconds} seconds</div>
      ) : (
        <div>Loading...</div>
      )}
    </div>
  );
}
