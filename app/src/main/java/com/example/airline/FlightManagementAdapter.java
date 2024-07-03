    package com.example.airline;

    import static androidx.core.content.ContextCompat.startActivity;

    import android.app.Activity;
    import android.content.Context;
    import android.content.Intent;
    import android.view.LayoutInflater;
    import android.view.View;
    import android.view.ViewGroup;
    import android.widget.Button;
    import android.widget.LinearLayout;
    import android.widget.TextView;
    import androidx.annotation.NonNull;
    import androidx.recyclerview.widget.RecyclerView;
    import java.time.format.DateTimeFormatter;
    import java.util.List;

//    public class FlightManagementAdapter extends RecyclerView.Adapter<FlightManagementAdapter.FlightViewHolder> {
//
//        private List<Flight> flightList;
//        private Context context;
//
//        public FlightManagementAdapter(List<Flight> flightList, Context context) {
//            this.flightList = flightList;
//            this.context = context;
//        }
//
//        @NonNull
//        @Override
//        public FlightViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.flight, parent, false);
//            return new FlightViewHolder(view);
//        }
//
//        @Override
//        public void onBindViewHolder(@NonNull FlightViewHolder holder, int position) {
//            Flight flight = flightList.get(position);
//            AirplaneDAO.getInstance(context).open();
//            // Bind initial visible details
//            holder.originDestination.setText(flight.getOrigin() + " - " + flight.getDestination());
//            holder.dateTime.setText(flight.getDateTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
//
//            // Bind additional details (hidden by default)
//            holder.flightNumber.setText("Flight Number: " + flight.getId());
//
////            holder.airplane.setText("Model: " + AirplaneDAO.getInstance(context).getAirplaneByName(flight.getAirplaneNameId()).getModel());
//
//            holder.staffList.setText("Staff List: " + flight.getStaffList().toString());
//            holder.customerList.setText("Customer List: " + flight.getCustomerList().toString());
//            holder.remainingCapacity.setText("Remaining Capacity: " + flight.getRemainingCapacity());
//            holder.price.setText("Price: " + flight.getPrice());
//
//            holder.expandButton.setOnClickListener(v -> {
//                boolean isVisible = holder.detailsLayout.getVisibility() == View.VISIBLE;
//                holder.detailsLayout.setVisibility(isVisible ? View.GONE : View.VISIBLE);
//                holder.expandButton.setText(isVisible ? "Show More" : "Show Less");
//            });
//
//            holder.editButton.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Intent intent = new Intent(context, EditFlightActivity.class);
//                    intent.putExtra("flightID", flight.getId());
//                    context.startActivity(intent);
//                }
//            });
//
//            holder.deleteButton.setOnClickListener(v -> {
//                // Handle delete action
//            });
//
//            AirplaneDAO.getInstance(context).close();
//        }
//
//        @Override
//        public int getItemCount() {
//            return flightList.size();
//        }
//
//        public static class FlightViewHolder extends RecyclerView.ViewHolder {
//            TextView originDestination, dateTime, flightNumber, airplane, staffList, customerList, remainingCapacity, price;
//            Button expandButton, editButton, deleteButton;
//            LinearLayout detailsLayout;
//
//            public FlightViewHolder(@NonNull View itemView) {
//                super(itemView);
//                originDestination = itemView.findViewById(R.id.originDestination);
//                dateTime = itemView.findViewById(R.id.dateTime);
//                expandButton = itemView.findViewById(R.id.expandButton);
//                detailsLayout = itemView.findViewById(R.id.detailsLayout);
//                flightNumber = itemView.findViewById(R.id.flightNumber);
//                airplane = itemView.findViewById(R.id.airplane);
//                staffList = itemView.findViewById(R.id.staffList);
//                customerList = itemView.findViewById(R.id.customerList);
//                remainingCapacity = itemView.findViewById(R.id.remainingCapacity);
//                price = itemView.findViewById(R.id.price);
//                editButton = itemView.findViewById(R.id.editButton);
//                deleteButton = itemView.findViewById(R.id.deleteButton);
//           }
//        }
//    }

//
//    public class FlightManagementAdapter extends RecyclerView.Adapter<FlightManagementAdapter.FlightViewHolder> {
//
//        private List<Flight> flightList;
//        private Context context;
//        public static final int REQUEST_CODE_EDIT_FLIGHT = 1;
//
//        public FlightManagementAdapter(List<Flight> flightList, Context context) {
//            this.flightList = flightList;
//            this.context = context;
//        }
//
//
//
//        @NonNull
//        @Override
//        public FlightViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.flight, parent, false);
//            return new FlightViewHolder(view);
//        }
//
//        @Override
//        public void onBindViewHolder(@NonNull FlightViewHolder holder, int position) {
//            Flight flight = flightList.get(position);
//            AirplaneDAO.getInstance(context).open();
//            // Bind initial visible details
//            holder.originDestination.setText(flight.getOrigin() + " - " + flight.getDestination());
//            holder.dateTime.setText(flight.getDateTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
//
//            // Bind additional details (hidden by default)
////            holder.flightNumber.setText("Flight Number: " + flight.getId());
////            holder.airplane.setText("Model: " + AirplaneDAO.getInstance(context).getAirplaneByName(flight.getAirplaneNameId()).getModel());
////            holder.staffList.setText("Staff List: " + flight.getStaffList().toString());
////            holder.customerList.setText("Customer List: " + flight.getCustomerList().toString());
////            holder.remainingCapacity.setText("Remaining Capacity: " + flight.getRemainingCapacity());
////            holder.price.setText("Price: " + flight.getPrice());
//
//            holder.expandButton.setOnClickListener(v -> {
//                boolean isVisible = holder.detailsLayout.getVisibility() == View.VISIBLE;
//                holder.detailsLayout.setVisibility(isVisible ? View.GONE : View.VISIBLE);
//                holder.expandButton.setText(isVisible ? "Show More" : "Show Less");
//            });
//
//            holder.editButton.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Intent intent = new Intent(context, EditFlightActivity.class);
//                    intent.putExtra("flightID", Integer.parseInt(flight.getId()));
//                    ((Activity) context).startActivityForResult(intent, REQUEST_CODE_EDIT_FLIGHT);
//                }
//            });
//
//            holder.deleteButton.setOnClickListener(v -> {
//                FlightDAO flightDAO = FlightDAO.getInstance(context);
//                flightDAO.open();
//                flightDAO.deleteFlightById(Integer.parseInt(flight.getId()));
//                flightDAO.close();
//                flightList.remove(position);
//                notifyItemRemoved(position);
//                notifyItemRangeChanged(position, flightList.size());
//            });
//
//            AirplaneDAO.getInstance(context).close();
//        }
//
//        @Override
//        public int getItemCount() {
//            return flightList.size();
//        }
//        public void setFlightList(List<Flight> flightList) {
//            this.flightList = flightList;
//        }
//
//
//        public static class FlightViewHolder extends RecyclerView.ViewHolder {
//            TextView originDestination, dateTime, flightNumber, airplane, staffList, customerList, remainingCapacity, price;
//            Button expandButton, editButton, deleteButton;
//            LinearLayout detailsLayout;
//
//            public FlightViewHolder(@NonNull View itemView) {
//                super(itemView);
//                originDestination = itemView.findViewById(R.id.originDestination);
//                dateTime = itemView.findViewById(R.id.dateTime);
//                expandButton = itemView.findViewById(R.id.expandButton);
//                detailsLayout = itemView.findViewById(R.id.detailsLayout);
//                flightNumber = itemView.findViewById(R.id.flightNumber);
//                airplane = itemView.findViewById(R.id.airplane);
//                staffList = itemView.findViewById(R.id.staffList);
//                customerList = itemView.findViewById(R.id.customerList);
//                remainingCapacity = itemView.findViewById(R.id.remainingCapacity);
//                price = itemView.findViewById(R.id.price);
//                editButton = itemView.findViewById(R.id.editButton);
//                deleteButton = itemView.findViewById(R.id.deleteButton);
//            }
//        }
//    }

    import android.app.Activity;
    import android.content.Context;
    import android.content.Intent;
    import android.view.LayoutInflater;
    import android.view.View;
    import android.view.ViewGroup;
    import android.widget.Button;
    import android.widget.LinearLayout;
    import android.widget.TextView;

    import androidx.annotation.NonNull;
    import androidx.recyclerview.widget.RecyclerView;

    import java.time.format.DateTimeFormatter;
    import java.util.List;
//
//    public class FlightManagementAdapter extends RecyclerView.Adapter<FlightManagementAdapter.FlightViewHolder> {
//
//        private List<Flight> flightList;
//        private Context context;
//        public static final int REQUEST_CODE_EDIT_FLIGHT = 1;
//
//        public FlightManagementAdapter(List<Flight> flightList, Context context) {
//            this.flightList = flightList;
//            this.context = context;
//        }
//
//        @NonNull
//        @Override
//        public FlightViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.flight, parent, false);
//            return new FlightViewHolder(view);
//        }
//
//        @Override
//        public void onBindViewHolder(@NonNull FlightViewHolder holder, int position) {
//            Flight flight = flightList.get(position);
//            AirplaneDAO.getInstance(context).open();
//            // Bind initial visible details
//            holder.originDestination.setText(flight.getOrigin() + " - " + flight.getDestination());
//            holder.dateTime.setText(flight.getDateTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
//
//            // Bind additional details (hidden by default)
//            holder.flightNumber.setText("Flight Number: " + flight.getId());
//            holder.airplane.setText("Model: " + AirplaneDAO.getInstance(context).getAirplaneByName(flight.getAirplaneNameId()).getModel());
//            holder.staffList.setText("Staff List: " + flight.getStaffList().toString());
//            holder.customerList.setText("Customer List: " + flight.getCustomerList().toString());
//            holder.remainingCapacity.setText("Remaining Capacity: " + flight.getRemainingCapacity());
//            holder.price.setText("Price: " + flight.getPrice());
//
//            holder.expandButton.setOnClickListener(v -> {
//                boolean isVisible = holder.detailsLayout.getVisibility() == View.VISIBLE;
//                holder.detailsLayout.setVisibility(isVisible ? View.GONE : View.VISIBLE);
//                holder.expandButton.setText(isVisible ? "Show More" : "Show Less");
//            });
//
//            holder.editButton.setOnClickListener(v -> {
//                Intent intent = new Intent(context, EditFlightActivity.class);
//                intent.putExtra("flightID", Integer.parseInt(flight.getId()));
//                ((Activity) context).startActivityForResult(intent, REQUEST_CODE_EDIT_FLIGHT);
//            });
//
//            holder.deleteButton.setOnClickListener(v -> {
//                FlightDAO flightDAO = FlightDAO.getInstance(context);
//                flightDAO.open();
//                flightDAO.deleteFlightById(Integer.parseInt(flight.getId()));
//                flightDAO.close();
//                flightList.remove(position);
//                notifyItemRemoved(position);
//                notifyItemRangeChanged(position, flightList.size());
//            });
//
//            AirplaneDAO.getInstance(context).close();
//        }
//
//        @Override
//        public int getItemCount() {
//            return flightList.size();
//        }
//
//        public void setFlightList(List<Flight> flightList) {
//            this.flightList = flightList;
//        }
//
//        public static class FlightViewHolder extends RecyclerView.ViewHolder {
//            TextView originDestination, dateTime, flightNumber, airplane, staffList, customerList, remainingCapacity, price;
//            Button expandButton, editButton, deleteButton;
//            LinearLayout detailsLayout;
//
//            public FlightViewHolder(@NonNull View itemView) {
//                super(itemView);
//                originDestination = itemView.findViewById(R.id.originDestination);
//                dateTime = itemView.findViewById(R.id.dateTime);
//                expandButton = itemView.findViewById(R.id.expandButton);
//                detailsLayout = itemView.findViewById(R.id.detailsLayout);
//                flightNumber = itemView.findViewById(R.id.flightNumber);
//                airplane = itemView.findViewById(R.id.airplane);
//                staffList = itemView.findViewById(R.id.staffList);
//                customerList = itemView.findViewById(R.id.customerList);
//                remainingCapacity = itemView.findViewById(R.id.remainingCapacity);
//                price = itemView.findViewById(R.id.price);
//                editButton = itemView.findViewById(R.id.editButton);
//                deleteButton = itemView.findViewById(R.id.deleteButton);
//            }
//        }
//    }

    import android.app.Activity;
    import android.content.Context;
    import android.content.Intent;
    import android.view.LayoutInflater;
    import android.view.View;
    import android.view.ViewGroup;
    import android.widget.Button;
    import android.widget.LinearLayout;
    import android.widget.TextView;

    import androidx.annotation.NonNull;
    import androidx.recyclerview.widget.RecyclerView;

    import java.time.format.DateTimeFormatter;
    import java.util.List;

//    public class FlightManagementAdapter extends RecyclerView.Adapter<FlightManagementAdapter.FlightViewHolder> {
//
//        private List<Flight> flightList;
//        private Context context;
//        public static final int REQUEST_CODE_EDIT_FLIGHT = 1;
//
//        public FlightManagementAdapter(List<Flight> flightList, Context context) {
//            this.flightList = flightList;
//            this.context = context;
//        }
//
//        @NonNull
//        @Override
//        public FlightViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.flight, parent, false);
//            return new FlightViewHolder(view);
//        }
//
//        @Override
//        public void onBindViewHolder(@NonNull FlightViewHolder holder, int position) {
//            Flight flight = flightList.get(position);
//            AirplaneDAO.getInstance(context).open();
//            // Bind initial visible details
//            holder.originDestination.setText(flight.getOrigin() + " - " + flight.getDestination());
//            holder.dateTime.setText(flight.getDateTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
//
//            // Bind additional details (hidden by default)
//            holder.flightNumber.setText("Flight Number: " + flight.getId());
////        holder.airplane.setText("Model: " + AirplaneDAO.getInstance(context).getAirplaneByName(flight.getAirplaneNameId()).getModel());
////        holder.staffList.setText("Staff List: " + flight.getStaffList().toString());
////        holder.customerList.setText("Customer List: " + flight.getCustomerList().toString());
////        holder.remainingCapacity.setText("Remaining Capacity: " + flight.getRemainingCapacity());
////        holder.price.setText("Price: " + flight.getPrice());
//
//            holder.expandButton.setOnClickListener(v -> {
//                boolean isVisible = holder.detailsLayout.getVisibility() == View.VISIBLE;
//                holder.detailsLayout.setVisibility(isVisible ? View.GONE : View.VISIBLE);
//                holder.expandButton.setText(isVisible ? "Show More" : "Show Less");
//            });
//
//            holder.editButton.setOnClickListener(v -> {
//                Intent intent = new Intent(context, EditFlightActivity.class);
//                intent.putExtra("flightID", Integer.parseInt(flight.getId()));
//                ((Activity) context).startActivityForResult(intent, REQUEST_CODE_EDIT_FLIGHT);
//            });
//
//            holder.deleteButton.setOnClickListener(v -> {
//                FlightDAO flightDAO = FlightDAO.getInstance(context);
//                flightDAO.open();
//                flightDAO.deleteFlightById(flight.getId());
//                flightDAO.close();
//                flightList.remove(position);
//                notifyItemRemoved(position);
//                notifyItemRangeChanged(position, flightList.size());
//            });
//        }
//
//        @Override
//        public int getItemCount() {
//            return flightList.size();
//        }
//
//        public static class FlightViewHolder extends RecyclerView.ViewHolder {
//            TextView originDestination, dateTime, flightNumber, airplane, staffList, customerList, remainingCapacity, price;
//            Button expandButton, editButton, deleteButton;
//            LinearLayout detailsLayout;
//
//            public FlightViewHolder(@NonNull View itemView) {
//                super(itemView);
//                originDestination = itemView.findViewById(R.id.originDestinationTextView);
//                dateTime = itemView.findViewById(R.id.dateTimeTextView);
//                flightNumber = itemView.findViewById(R.id.flightNumberTextView);
//                airplane = itemView.findViewById(R.id.airplaneTextView);
//                staffList = itemView.findViewById(R.id.staffListTextView);
//                customerList = itemView.findViewById(R.id.customerListTextView);
//                remainingCapacity = itemView.findViewById(R.id.remainingCapacityTextView);
//                price = itemView.findViewById(R.id.priceTextView);
//
//                expandButton = itemView.findViewById(R.id.expandButton);
//                editButton = itemView.findViewById(R.id.editButton);
//                deleteButton = itemView.findViewById(R.id.deleteButton);
//
//                detailsLayout = itemView.findViewById(R.id.detailsLayout);
//            }
//        }
//    }


    import android.app.Activity;
    import android.content.Context;
    import android.content.Intent;
    import android.view.LayoutInflater;
    import android.view.View;
    import android.view.ViewGroup;
    import android.widget.Button;
    import android.widget.LinearLayout;
    import android.widget.TextView;
    import android.widget.Toast;

    import androidx.annotation.NonNull;
    import androidx.recyclerview.widget.RecyclerView;

    import java.time.format.DateTimeFormatter;
    import java.util.List;

    public class FlightManagementAdapter extends RecyclerView.Adapter<FlightManagementAdapter.FlightViewHolder> {

        private List<Flight> flightList;
        private Context context;
        public static final int REQUEST_CODE_EDIT_FLIGHT = 1;

        public FlightManagementAdapter(List<Flight> flightList, Context context) {
            this.flightList = flightList;
            this.context = context;
        }

        @NonNull
        @Override
        public FlightViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.flight, parent, false);
            return new FlightViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull FlightViewHolder holder, int position) {
            Flight flight = flightList.get(position);

            // Bind initial visible details
            holder.originDestination.setText(flight.getOrigin() + " - " + flight.getDestination());
            holder.dateTime.setText(flight.getDateTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));

            // Bind additional details (hidden by default)
            holder.flightNumber.setText("Flight Number: " + flight.getId());
            holder.airplane.setText("Airplane: " + flight.getAirplaneNameId());
            holder.staffList.setText("Staff List: " + flight.getStaffList().toString());
            holder.customerList.setText("Customer List: " + flight.getCustomerList().toString());
            holder.remainingCapacity.setText("Remaining Capacity: " + flight.getRemainingCapacity());
            holder.price.setText("Price: " + flight.getPrice());

            holder.expandButton.setOnClickListener(v -> {
                boolean isVisible = holder.detailsLayout.getVisibility() == View.VISIBLE;
                holder.detailsLayout.setVisibility(isVisible ? View.GONE : View.VISIBLE);
                holder.expandButton.setText(isVisible ? "Show More" : "Show Less");
            });

            holder.editButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, EditFlightActivity.class);
//                    Toast.makeText(context, String.valueOf(flight.getId()), Toast.LENGTH_SHORT).show();
                    intent.putExtra("flightID", Integer.valueOf(flight.getId()));
                    ((Activity) context).startActivityForResult(intent, REQUEST_CODE_EDIT_FLIGHT);
                }
            });

            holder.deleteButton.setOnClickListener(v -> {
                FlightDAO flightDAO = FlightDAO.getInstance(context);
                flightDAO.open();
                flightDAO.deleteFlightById(Integer.valueOf(flight.getId()));
                flightDAO.close();
                flightList.remove(position);
                notifyItemRemoved(position);
                notifyItemRangeChanged(position, flightList.size());
            });
        }

        @Override
        public int getItemCount() {
            return flightList.size();
        }

        public void setFlightList(List<Flight> flightList) {
            this.flightList = flightList;
        }

        public static class FlightViewHolder extends RecyclerView.ViewHolder {
            TextView originDestination, dateTime, flightNumber, airplane, staffList, customerList, remainingCapacity, price;
            Button expandButton, editButton, deleteButton;
            LinearLayout detailsLayout;

            public FlightViewHolder(@NonNull View itemView) {
                super(itemView);
                originDestination = itemView.findViewById(R.id.originDestination);
                dateTime = itemView.findViewById(R.id.dateTime);
                expandButton = itemView.findViewById(R.id.expandButton);
                detailsLayout = itemView.findViewById(R.id.detailsLayout);
                flightNumber = itemView.findViewById(R.id.flightNumber);
                airplane = itemView.findViewById(R.id.airplane);
                staffList = itemView.findViewById(R.id.staffList);
                customerList = itemView.findViewById(R.id.customerList);
                remainingCapacity = itemView.findViewById(R.id.remainingCapacity);
                price = itemView.findViewById(R.id.price);
                editButton = itemView.findViewById(R.id.editButton);
                deleteButton = itemView.findViewById(R.id.deleteButton);
            }
        }
    }
