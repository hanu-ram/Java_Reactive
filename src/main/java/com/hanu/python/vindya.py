from datetime import timedelta, datetime

# Define delivery points and their zones
delivery_points = {
    "D1": {"zone": 1, "coordinates": (4, 5), "access_time": ("09:00", "17:00")},
    "D2": {"zone": 1, "coordinates": (4, 8), "access_time": ("09:00", "17:00")},
    "D3": {"zone": 2, "coordinates": (7, 2), "access_time": ("10:00", "15:00")},
    "D4": {"zone": 3, "coordinates": (6, 6), "access_time": ("08:00", "18:00")},
    "D5": {"zone": 4, "coordinates": (2, 4), "access_time": None},  # No restrictions
    "D6": {"zone": 5, "coordinates": (9, 1), "access_time": None},  # No restrictions
}
# Travel time matrix (in minutes)
travel_times = [
    [0, 3, 7, 9, 5, 10],  # D1
    [3, 0, 4, 6, 8, 12],  # D2
    [7, 4, 0, 3, 10, 5],  # D3
    [9, 6, 3, 0, 8, 7],  # D4
    [5, 8, 10, 8, 0, 6],  # D5
    [10, 12, 5, 7, 6, 0]  # D6
]

# Map delivery points to indices in the travel_times matrix
point_indices = {"D1": 0, "D2": 1, "D3": 2, "D4": 3, "D5": 4, "D6": 5}

# Battery capacity in minutes
battery_capacity = 100


# Helper functions
def time_in_window(current_time, time_window):
    """Check if the current time is within a specific time window (HH:MM format)."""
    if time_window is None:
        return True  # No restrictions

    start_time = datetime.strptime(time_window[0], "%H:%M").time()
    end_time = datetime.strptime(time_window[1], "%H:%M").time()
    return start_time <= current_time <= end_time


def a_star_search(start, deliveries, start_time):
    from heapq import heappop, heappush
    queue = []
    heappush(queue, (0, start, [start], 0, start_time))  # (cost, current_point, path, battery_used, current_time)
    best_routes = []

    while queue:
        cost, current, path, battery_used, current_time = heappop(queue)

        # Check if all deliveries are completed
        if len(path) - 1 == len(deliveries):
            best_routes.append((cost, path, battery_used))
            continue

        # Explore neighbors
        current_index = point_indices[current]
        for next_point, details in deliveries.items():
            if next_point in path:
                continue  # Skip visited points

            next_index = point_indices[next_point]
            travel_time = travel_times[current_index][next_index]
            arrival_time = (datetime.combine(datetime.today(), current_time) + timedelta(minutes=travel_time)).time()

            # Print debug information
            print(f"Evaluating move from {current} to {next_point}:")
            print(f"  Arrival Time: {arrival_time}, Access Window: {details['access_time']}")
            print(f"  Battery Used: {battery_used + travel_time}, Battery Limit: {battery_capacity}")

            # Check access time window and battery constraints
            if time_in_window(arrival_time, details["access_time"]) and battery_used + travel_time <= battery_capacity:
                heappush(queue, (
                    cost
                    + travel_time,
                    next_point,
                    path + [next_point],
                    battery_used + travel_time,
                    arrival_time,
                ))

                # Select the route with the least cost if multiple routes are found
    return min(best_routes, key=lambda x: x[0]) if best_routes else None


# Main function to generate routes for drones
def generate_routes():
    # Example setup for two drones
    drones = {
        "Drone 1": ["D1", "D3", "D4", "D5"],
        "Drone 2": ["D2", "D6"]
    }
    results = {}
    for drone, delivery_points_keys in drones.items():
        deliveries = {dp: delivery_points[dp] for dp in delivery_points_keys}
        start_time = datetime.strptime("08:00", "%H:%M").time()  # Assume starting time is 8:00 AM
        route = a_star_search("D1", deliveries, start_time)

        if route:
            total_time, path, battery_used = route
            results[drone] = {
                "Route": path,
                "Total Travel Time": total_time,
                "Battery Usage": f"{battery_used}%",
                "All deliveries met access restrictions": "Yes"
            }
        else:
            results[drone] = {
                "Route": [],
                "Total Travel Time": "N/A",
                "Battery Usage": "N/A",
                "All deliveries met access restrictions": "No"
            }
    return results


# Display results
routes = generate_routes()
for drone, info in routes.items():
    print(f"{drone} Route:")
    print("Path:", " -> ".join(info["Route"]))
    print("Total Travel Time:", info["Total Travel Time"])
    print("Battery Usage:", info["Battery Usage"])
    print("All deliveries met access restrictions:", info["All deliveries met access restrictions"])
    print("\n")
