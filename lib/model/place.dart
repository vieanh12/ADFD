class Place {
  final int id;
  final String name;
  final String image;
  final double rating;

  Place({required this.id, required this.name, required this.image, required this.rating});

  factory Place.fromJson(Map<String, dynamic> json) {
    return Place(
      id: json['id'],
      name: json['name'],
      image: json['image'],
      rating: json['rating'].toDouble(),
    );
  }
}
